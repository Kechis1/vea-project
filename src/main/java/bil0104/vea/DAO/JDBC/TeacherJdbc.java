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
public class TeacherJdbc implements TeacherDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert personInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        /* jdbcTemplate = new JdbcTemplate(dataSource);
        personInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("Person").usingGeneratedKeyColumns("id")
                .usingColumns("firstName", "secondName", "age");*/
    }

    @PostConstruct
    public void init() {
        try {
            String dbProducerName;
            try (Connection con = jdbcTemplate.getDataSource().getConnection()) {
                DatabaseMetaData metaData = con.getMetaData();
                dbProducerName = metaData.getDatabaseProductName();
            }
            System.out.println(dbProducerName);
/*            String sqlCreateTable;
            if ("Apache Derby".equals(dbProducerName)) {
                sqlCreateTable = "CREATE TABLE Person (id INTEGER NOT NULL "
                        + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                        + "firstName varchar(255), secondName varchar(255), "
                        + "age int";
            } else if ("H2".equals(dbProducerName)) {
                sqlCreateTable = "create table Person(id INTEGER NOT NULL " + "AUTO_INCREMENT," + " firstName varchar(255), "
                        + "secondName varchar(255), " + "age int);";
            } else {
                throw new RuntimeException("Unsuported database type");
            }
            jdbcTemplate.update(sqlCreateTable);*/
        } catch (DataAccessException e) {
            System.out.println("Table already exist.");
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
