package bil0104.vea.DAO.JDBC;

import java.sql.*;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import bil0104.vea.DAO.JDBC.Mappers.SubjectMapper;
import bil0104.vea.DAO.JDBC.Mappers.TeacherMapper;
import bil0104.vea.DAO.TeacherDao;
import bil0104.vea.Entities.Subject;
import bil0104.vea.Entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


@Repository
public class TeacherDaoJdbc implements TeacherDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @PostConstruct
    public void init() {
        try {
            String dbProducerName;
            try (Connection con = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection()) {
                DatabaseMetaData metaData = con.getMetaData();
                dbProducerName = metaData.getDatabaseProductName();
            }
            String sqlCreateTable;
            if ("H2".equals(dbProducerName)) {
                sqlCreateTable = "CREATE TABLE IF NOT EXISTS teachers(id BIGINT NOT NULL AUTO_INCREMENT," +
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
    public Teacher insert(Teacher teacher) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into teachers (login, firstname, lastname, dateofbirth, password, role) values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, teacher.getLogin());
            ps.setString(2, teacher.getFirstName());
            ps.setString(3, teacher.getLastName());
            ps.setDate(4, new Date(teacher.getDateOfBirth().getTime()));
            ps.setString(5, teacher.getPassword());
            ps.setString(6, teacher.getRole().toString());
            return ps;
        }, keyHolder);
        teacher.setId((long) keyHolder.getKey());
        return teacher;
    }

    @Override
    public List<Teacher> getAll() {
        List<Teacher> teachers = jdbcTemplate.query("select * from teachers", new TeacherMapper());
        for (Teacher t : teachers) {
            List<Subject> subjects = jdbcTemplate.query("select * from subjects where teacher_id = ?", new Object[]{t.getId()}, new SubjectMapper());
            subjects.forEach(x -> x.setTeacher(t));
            t.setTeaches(subjects);
        }
        return teachers;
    }

    @Override
    public Teacher findById(long id) {
        try {
            Teacher teacher = jdbcTemplate.queryForObject("select * from teachers where id=?", new Object[]{id}, new TeacherMapper());
            if (teacher != null) {
                List<Subject> subjects = jdbcTemplate.query("select * from subjects where teacher_id = ?", new Object[]{id}, new SubjectMapper());
                subjects.forEach(x -> x.setTeacher(teacher));
                teacher.setTeaches(subjects);
            }
            return teacher;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Teacher update(Teacher teacher) {
        jdbcTemplate.update("UPDATE teachers SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?",
                teacher.getFirstName(), teacher.getLastName(), teacher.getDateOfBirth(), teacher.getId());
        return teacher;
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM teachers WHERE id = ?", id);
    }

    @Override
    public Teacher findByLogin(String login) {
        try {
            Teacher teacher = jdbcTemplate.queryForObject("select * from teachers where login=?", new Object[]{login}, new TeacherMapper());
            if (teacher != null) {
                List<Subject> subjects = jdbcTemplate.query("select * from subjects where teacher_id = ?", new Object[]{teacher.getId()}, new SubjectMapper());
                subjects.forEach(x -> x.setTeacher(teacher));
                teacher.setTeaches(subjects);
            }
            return teacher;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
