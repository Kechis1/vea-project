package bil0104.vea.DAO.JDBC;

import bil0104.vea.DAO.StudyDao;
import bil0104.vea.Entities.Person;
import bil0104.vea.Entities.Study;
import bil0104.vea.Entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudyDaoJdbc implements StudyDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert studyInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        studyInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("studies").usingGeneratedKeyColumns("id")
                .usingColumns("year", "points", "student_id", "subject_id");
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
                sqlCreateTable = "create table studies(id BIGINT NOT NULL AUTO_INCREMENT," +
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
    public void insert(Study study) {

    }

    @Override
    public List<Study> getAll() {
        return null;
    }

    @Override
    public List<Study> findByStudentAndYear(Person person, String year) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Study find(long id) {
        return null;
    }

    @Override
    public void update(Study study) {

    }

    @Override
    public Study findByUniqueKey(long studentId, long subjectId, String year) {
        return null;
    }

    @Override
    public void deleteWhereSubjectId(long id) {

    }

    @Override
    public void deleteWhereStudentId(long id) {

    }

    @Override
    public List<Study> findBySubjectAndYear(Subject subject, String year) {
        return null;
    }
}
