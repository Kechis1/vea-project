package bil0104.vea.DAO.JDBC.Mappers;

import bil0104.vea.Entities.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Student(rs.getLong("id"),
                rs.getString("login"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getDate("dateOfBirth"),
                rs.getString("password"),
               null,
                rs.getInt("year")
                );
    }
}