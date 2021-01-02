package bil0104.vea.DAO.JDBC;

import bil0104.vea.DAO.PersonDao;
import bil0104.vea.Entities.Person;
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
public class PersonDaoJdbc implements PersonDao<Person> {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert personInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        personInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("persons").usingGeneratedKeyColumns("id")
                .usingColumns("login", "firstName", "lastName", "dateOfBirth", "password", "role");
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
                sqlCreateTable = "create table persons(id BIGINT NOT NULL AUTO_INCREMENT," +
                        " login varchar(10) not null, " +
                        " firstName varchar(255) not null, " +
                        " lastName varchar(255) not null, " +
                        " dateOfBirth date, " +
                        " password varchar (128) not null, " +
                        " role varchar (20) not null)";
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
    public Person insert(Person entity) {
        return null;
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public Person findById(long id) {
        return null;
    }

    @Override
    public Person update(Person entity) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Person findByLogin(String login) {
        return null;
    }
}
