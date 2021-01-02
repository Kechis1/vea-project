package bil0104.vea.DAO.JDBC;

import bil0104.vea.DAO.JDBC.Mappers.PersonMapper;
import bil0104.vea.DAO.PersonDao;
import bil0104.vea.Entities.Person;
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
public class PersonDaoJdbc implements PersonDao<Person> {
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
    public List<Person> getAll() {
        return jdbcTemplate.query("select * from persons", new PersonMapper());
    }

    @Override
    public Person findById(long id) {
        try {
            return jdbcTemplate.queryForObject("select * from persons where id=?", new Object[]{id}, new PersonMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Person insert(Person person) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into persons (login, firstname, lastname, dateofbirth, password, role) values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, person.getLogin());
            ps.setString(2, person.getFirstName());
            ps.setString(3, person.getLastName());
            ps.setDate(4, new Date(person.getDateOfBirth().getTime()));
            ps.setString(5, person.getPassword());
            ps.setString(6, person.getRole().toString());
            return ps;
        }, keyHolder);
        person.setId((long) keyHolder.getKey());
        return person;
    }

    @Override
    public Person update(Person person) {
        jdbcTemplate.update("UPDATE persons SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?",
                person.getFirstName(), person.getLastName(), person.getDateOfBirth(), person.getId());
        return person;
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM persons WHERE id = ?", id);
    }

    @Override
    public Person findByLogin(String login) {
        try {
            return jdbcTemplate.queryForObject("select * from persons where login=?", new Object[] {login} , new PersonMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
