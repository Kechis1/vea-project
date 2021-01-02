package bil0104.vea.DAO.JDBC;

import bil0104.vea.DAO.JDBC.Mappers.StudentMapper;
import bil0104.vea.DAO.JDBC.Mappers.StudyMapper;
import bil0104.vea.DAO.JDBC.Mappers.SubjectMapper;
import bil0104.vea.DAO.JDBC.Mappers.TeacherMapper;
import bil0104.vea.DAO.SubjectDao;
import bil0104.vea.Entities.Student;
import bil0104.vea.Entities.Study;
import bil0104.vea.Entities.Subject;
import bil0104.vea.Entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository
public class SubjectDaoJdbc implements SubjectDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @PostConstruct
    public void init() {
        try {
            String dbProducerName;
            try (Connection con = jdbcTemplate.getDataSource().getConnection()) {
                DatabaseMetaData metaData = con.getMetaData();
                dbProducerName = metaData.getDatabaseProductName();
            }
            String sqlCreateTable;
            if ("H2".equals(dbProducerName)) {
                sqlCreateTable = "create table subjects(id BIGINT NOT NULL AUTO_INCREMENT," +
                        " abbreviation varchar(10) not null, " +
                        " name varchar(255) not null, " +
                        " year smallint not null, " +
                        " semester varchar(10) not null , " +
                        " credits int not null, " +
                        " teacher_id int);";
            } else {
                throw new RuntimeException("Unsupported database type");
            }
            jdbcTemplate.update(sqlCreateTable);
        } catch (DataAccessException e) {
            System.out.println("Table already exists.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Subject insert(Subject subject) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            String sql = subject.getTeacher() == null ?
                    "insert into subjects (abbreviation, name, year, semester, credits, teacher_id) values (?,?,?,?,?,null)" :
                    "insert into subjects (abbreviation, name, year, semester, credits, teacher_id) values (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, subject.getAbbreviation());
            ps.setString(2, subject.getName());
            ps.setInt(3, subject.getYear());
            ps.setString(4, subject.getSemester().toString());
            ps.setInt(5, subject.getCredits());
            if (subject.getTeacher() != null) {
                ps.setLong(6, subject.getTeacher().getId());
            }
            return ps;
        }, keyHolder);
        subject.setId((long) keyHolder.getKey());
        return subject;
    }

    @Override
    public List<Subject> getAll() {
        List<Subject> subjects = jdbcTemplate.query("select * from subjects", new SubjectMapper());
        for (Subject t : subjects) {
            if (t.getTeacherId() != 0) {
                Teacher teacher = jdbcTemplate.queryForObject("select * from teachers where id = ?", new Object[]{t.getTeacherId()}, new TeacherMapper());
                t.setTeacher(teacher);
            }
            List<Study> studies = jdbcTemplate.query("select st.id st_id, st.year st_year, st.points st_points, st.subject_id, st.student_id, stu.id stu_id, stu.firstname stu_firstname, stu.lastname stu_lastname, stu.year stu_year, stu.login stu_login, stu.dateofbirth stu_dateofbirth, sub.id sub_id, sub.abbreviation sub_abbreviation, sub.name sub_name, sub.year sub_year, sub.semester sub_semester, sub.credits sub_credits, sub.teacher_id from studies st join students stu on st.student_id = stu.id join subjects sub on st.subject_id = sub.id where st.subject_id = ?", new Object[] {t.getId()}, new StudyMapper());
            t.setStudies(studies);
        }
        return subjects;
    }

    @Override
    public Subject findById(long id) {
        Subject subject = jdbcTemplate.queryForObject("select * from subjects where id = ?", new Object[]{id}, new SubjectMapper());
        if (subject != null) {
            if (subject.getTeacherId() != 0) {
                Teacher teacher = jdbcTemplate.queryForObject("select * from teachers where id = ?", new Object[]{subject.getTeacherId()}, new TeacherMapper());
                subject.setTeacher(teacher);
            }
            List<Study> studies = jdbcTemplate.query("select st.id st_id, st.year st_year, st.points st_points, st.subject_id, st.student_id, stu.id stu_id, stu.firstname stu_firstname, stu.lastname stu_lastname, stu.year stu_year, stu.login stu_login, stu.dateofbirth stu_dateofbirth, sub.id sub_id, sub.abbreviation sub_abbreviation, sub.name sub_name, sub.year sub_year, sub.semester sub_semester, sub.credits sub_credits, sub.teacher_id from studies st join students stu on st.student_id = stu.id join subjects sub on st.subject_id = sub.id where st.subject_id = ?", new Object[]{subject.getId()}, new StudyMapper());
            subject.setStudies(studies);
        }
        return subject;
    }

    @Override
    public Subject update(Subject subject) {
        jdbcTemplate.update("UPDATE subjects SET abbreviation = ?, name = ?, year = ?, semester = ?, credits = ?, teacher_id = ? WHERE id = ?",
                subject.getAbbreviation(), subject.getName(), subject.getYear(), subject.getSemester().toString(), subject.getCredits(), subject.getTeacher().getId(), subject.getId());
        return subject;
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM subjects WHERE id = ?", id);
    }

    @Override
    public List<Subject> getWithoutStudent(long id) {
        return jdbcTemplate.query("select * from subjects s where s.id not in (select st.subject_id from studies st where st.student_id = ?)", new Object[] {id}, new SubjectMapper());
    }

    @Override
    public List<Subject> getWithoutTeacher() {
        return jdbcTemplate.query("select * from subjects where teacher_id is null", new SubjectMapper());
    }

    @Override
    public void detachTeacher(long id) {
        jdbcTemplate.update("UPDATE subjects SET teacher_id = null WHERE teacher_id = ?", id);
    }

    @Override
    public void delete(Subject subject) {
        jdbcTemplate.update("DELETE FROM subjects WHERE id = ?", subject.getId());
    }
}
