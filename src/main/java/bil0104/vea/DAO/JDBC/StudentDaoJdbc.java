package bil0104.vea.DAO.JDBC;

import bil0104.vea.DAO.JDBC.Mappers.StudentMapper;
import bil0104.vea.DAO.JDBC.Mappers.StudyMapper;
import bil0104.vea.DAO.StudentDao;
import bil0104.vea.Entities.Student;
import bil0104.vea.Entities.Study;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository
public class StudentDaoJdbc implements StudentDao {
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
                sqlCreateTable = "create table students(id BIGINT NOT NULL AUTO_INCREMENT," +
                        " login varchar(10) not null, " +
                        " firstName varchar(255) not null, " +
                        " lastName varchar(255) not null, " +
                        " dateOfBirth date, " +
                        " password varchar (128) not null, " +
                        " role varchar (20) not null, " +
                        " year int)";
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
    public Student insert(Student student) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into students (login, firstname, lastname, dateofbirth, password, role, year) values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getLogin());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());
            ps.setDate(4, new Date(student.getDateOfBirth().getTime()));
            ps.setString(5, student.getPassword());
            ps.setString(6, student.getRole().toString());
            ps.setInt(7, student.getYear());
            return ps;
        }, keyHolder);
        student.setId((long) keyHolder.getKey());
        return student;
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = jdbcTemplate.query("select * from students", new StudentMapper());
        for (Student t : students) {
            List<Study> studies = jdbcTemplate.query("select st.id st_id, st.year st_year, st.points st_points, st.subject_id, st.student_id, stu.id stu_id, stu.firstname stu_firstname, stu.lastname stu_lastname, stu.year stu_year, stu.login stu_login, stu.dateofbirth stu_dateofbirth, sub.id sub_id, sub.abbreviation sub_abbreviation, sub.name sub_name, sub.year sub_year, sub.semester sub_semester, sub.credits sub_credits, sub.teacher_id from studies st join students stu on st.student_id = stu.id join subjects sub on st.subject_id = sub.id where st.student_id = ?", new Object[] {t.getId()}, new StudyMapper());
            t.setStudies(studies);
        }
        return students;
    }

    @Override
    public Student findById(long id) {
        try {
            Student student = jdbcTemplate.queryForObject("select * from students where id = ?", new Object[]{id}, new StudentMapper());
            if (student != null) {
                List<Study> studies = jdbcTemplate.query("select st.id st_id, st.year st_year, st.points st_points, st.subject_id, st.student_id, stu.id stu_id, stu.firstname stu_firstname, stu.lastname stu_lastname, stu.year stu_year, stu.login stu_login, stu.dateofbirth stu_dateofbirth, sub.id sub_id, sub.abbreviation sub_abbreviation, sub.name sub_name, sub.year sub_year, sub.semester sub_semester, sub.credits sub_credits, sub.teacher_id from studies st join students stu on st.student_id = stu.id join subjects sub on st.subject_id = sub.id where st.student_id = ?", new Object[]{id}, new StudyMapper());
                student.setStudies(studies);
            }
            return student;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Student update(Student student) {
        jdbcTemplate.update("UPDATE students SET firstname = ?, lastname = ?, dateofbirth = ?, year = ? WHERE id = ?",
                student.getFirstName(), student.getLastName(), student.getDateOfBirth(), student.getYear(), student.getId());
        return student;
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM students WHERE id = ?", id);
    }

    @Override
    public Student findByLogin(String login) {
        try {
            Student student = jdbcTemplate.queryForObject("select * from students where login=?", new Object[]{login}, new StudentMapper());
            if (student != null) {
                List<Study> studies = jdbcTemplate.query("select st.id st_id, st.year st_year, st.points st_points, st.subject_id, st.student_id, stu.id stu_id, stu.firstname stu_firstname, stu.lastname stu_lastname, stu.year stu_year, stu.login stu_login, stu.dateofbirth stu_dateofbirth, sub.id sub_id, sub.abbreviation sub_abbreviation, sub.name sub_name, sub.year sub_year, sub.semester sub_semester, sub.credits sub_credits, sub.teacher_id from studies st join students stu on st.student_id = stu.id join subjects sub on st.subject_id = sub.id where st.student_id = ?", new Object[]{student.getId()}, new StudyMapper());
                student.setStudies(studies);
            }
            return student;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
