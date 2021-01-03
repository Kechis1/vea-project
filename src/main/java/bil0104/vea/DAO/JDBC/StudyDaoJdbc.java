package bil0104.vea.DAO.JDBC;

import bil0104.vea.DAO.JDBC.Mappers.StudyMapper;
import bil0104.vea.DAO.StudyDao;
import bil0104.vea.Entities.Person;
import bil0104.vea.Entities.Study;
import bil0104.vea.Entities.Subject;
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
public class StudyDaoJdbc implements StudyDao {

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
                sqlCreateTable = "CREATE TABLE IF NOT EXISTS studies(id BIGINT NOT NULL AUTO_INCREMENT," +
                        " year varchar(12) not null, " +
                        " points int, " +
                        " student_id int not null, " +
                        " subject_id int not null, " +
                        " unique (year, student_id, subject_id))";
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
    public Study insert(Study study) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into studies (year, points, student_id, subject_id) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, study.getYear());
            ps.setInt(2, study.getPoints());
            ps.setLong(3, study.getStudent().getId());
            ps.setLong(4, study.getSubject().getId());
            return ps;
        }, keyHolder);
        study.setId((long) keyHolder.getKey());
        return study;
    }

    @Override
    public List<Study> getAll() {
        return jdbcTemplate.query("select st.id st_id, st.year st_year, st.points st_points, st.subject_id, st.student_id, stu.id stu_id, stu.firstname stu_firstname, stu.lastname stu_lastname, stu.year stu_year, stu.login stu_login, stu.dateofbirth stu_dateofbirth, sub.id sub_id, sub.abbreviation sub_abbreviation, sub.name sub_name, sub.year sub_year, sub.semester sub_semester, sub.credits sub_credits, sub.teacher_id from studies st join students stu on st.student_id = stu.id join subjects sub on st.subject_id = sub.id", new StudyMapper());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM studies WHERE id = ?", id);
    }

    @Override
    public List<Study> findByStudentAndYear(Person person, String year) {
        return jdbcTemplate.query("select st.id st_id, st.year st_year, st.points st_points, st.subject_id, st.student_id, stu.id stu_id, stu.firstname stu_firstname, stu.lastname stu_lastname, stu.year stu_year, stu.login stu_login, stu.dateofbirth stu_dateofbirth, sub.id sub_id, sub.abbreviation sub_abbreviation, sub.name sub_name, sub.year sub_year, sub.semester sub_semester, sub.credits sub_credits, sub.teacher_id, t.id t_id, t.firstname t_firstname, t.lastname t_lastname, t.password t_password from studies st join students stu on st.student_id = stu.id join subjects sub on st.subject_id = sub.id left join teachers t on sub.teacher_id = t.id where st.student_id = ? and st.year = ?", new Object[]{person.getId(), year}, new StudyMapper());
    }

    @Override
    public Study findById(long id) {
        try {
            return jdbcTemplate.queryForObject("select st.id st_id, st.year st_year, st.points st_points, st.subject_id, st.student_id, stu.id stu_id, stu.firstname stu_firstname, stu.lastname stu_lastname, stu.year stu_year, stu.login stu_login, stu.dateofbirth stu_dateofbirth, sub.id sub_id, sub.abbreviation sub_abbreviation, sub.name sub_name, sub.year sub_year, sub.semester sub_semester, sub.credits sub_credits, sub.teacher_id from studies st join students stu on st.student_id = stu.id join subjects sub on st.subject_id = sub.id where st.id = ?", new Object[]{id}, new StudyMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Study findByUniqueKey(long studentId, long subjectId, String year) {
        try {
            return jdbcTemplate.queryForObject("select st.id st_id, st.year st_year, st.points st_points, st.subject_id, st.student_id, stu.id stu_id, stu.firstname stu_firstname, stu.lastname stu_lastname, stu.year stu_year, stu.login stu_login, stu.dateofbirth stu_dateofbirth, sub.id sub_id, sub.abbreviation sub_abbreviation, sub.name sub_name, sub.year sub_year, sub.semester sub_semester, sub.credits sub_credits, sub.teacher_id from studies st join students stu on st.student_id = stu.id join subjects sub on st.subject_id = sub.id where st.student_id = ? and st.subject_id = ? and st.year = ?", new Object[]{studentId, subjectId, year}, new StudyMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteWhereSubjectId(long id) {
        jdbcTemplate.update("DELETE FROM studies WHERE subject_id = ?", id);
    }

    @Override
    public void deleteWhereStudentId(long id) {
        jdbcTemplate.update("DELETE FROM studies WHERE student_id = ?", id);
    }

    @Override
    public Study update(Study study) {
        jdbcTemplate.update("UPDATE studies SET points = ? WHERE id = ?",
                study.getPoints(), study.getId());
        return study;
    }

    @Override
    public List<Study> findBySubjectAndYear(Subject subject, String year) {
        return jdbcTemplate.query("select st.id st_id, st.year st_year, st.points st_points, st.subject_id, st.student_id, stu.id stu_id, stu.firstname stu_firstname, stu.lastname stu_lastname, stu.year stu_year, stu.login stu_login, stu.dateofbirth stu_dateofbirth, sub.id sub_id, sub.abbreviation sub_abbreviation, sub.name sub_name, sub.year sub_year, sub.semester sub_semester, sub.credits sub_credits, sub.teacher_id from studies st join students stu on st.student_id = stu.id join subjects sub on st.subject_id = sub.id where st.subject_id = ? and st.year = ?", new Object[]{subject.getId(), year}, new StudyMapper());
    }
}
