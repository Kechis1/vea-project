package bil0104.vea.DAO.JDBC;

import bil0104.vea.DAO.SubjectDao;
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
public class SubjectDaoJdbc implements SubjectDao {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert subjectInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        subjectInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("subjects").usingGeneratedKeyColumns("id")
                .usingColumns("abbreviation", "name", "year", "semester", "credits", "teacher_id");
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
    public Subject insert(Subject entity) {
        return null;
    }

    @Override
    public List<Subject> getAll() {
        return null;
    }

    @Override
    public Subject findById(long id) {
        return null;
    }

    @Override
    public Subject update(Subject entity) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Subject> getWithoutStudent(long id) {
        return null;
    }

    @Override
    public List<Subject> getWithoutTeacher() {
        return null;
    }

    @Override
    public void delete(Subject subject) {

    }

    @Override
    public void detachTeacher(long id) {

    }
}
