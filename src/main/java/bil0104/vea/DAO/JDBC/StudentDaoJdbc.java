package bil0104.vea.DAO.JDBC;

import bil0104.vea.DAO.StudentDao;
import bil0104.vea.Entities.Student;
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
public class StudentDaoJdbc implements StudentDao {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert studentInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        studentInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("students").usingGeneratedKeyColumns("id")
                .usingColumns("login", "firstName", "lastName", "dateOfBirth", "password", "role", "year");
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
    public Student insert(Student entity) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public Student findById(long id) {
        return null;
    }

    @Override
    public Student update(Student entity) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Student findByLogin(String login) {
        return null;
    }
}
