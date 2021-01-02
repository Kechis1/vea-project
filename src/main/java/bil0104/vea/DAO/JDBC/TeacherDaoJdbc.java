package bil0104.vea.DAO.JDBC;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import bil0104.vea.DAO.TeacherDao;
import bil0104.vea.Entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


@Repository
public class TeacherDaoJdbc implements TeacherDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert teacherInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        teacherInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("teachers").usingGeneratedKeyColumns("id")
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
                sqlCreateTable = "create table teachers(id BIGINT NOT NULL AUTO_INCREMENT," +
                        " login varchar(10) not null, " +
                        " firstName varchar(255) not null, " +
                        " lastName varchar(255) not null, " +
                        " dateOfBirth date, " +
                        " password varchar (128) not null, " +
                        " role varchar (20) not null )";
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
    public Teacher insert(Teacher entity) {
        return null;
    }

    @Override
    public List<Teacher> getAll() {
        return null;
    }

    @Override
    public Teacher findById(long id) {
        return null;
    }

    @Override
    public Teacher update(Teacher entity) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Teacher findByLogin(String login) {
        return null;
    }
}
