package bil0104.vea.DAO.JDBC.Mappers;

import bil0104.vea.Entities.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Teacher(rs.getLong("id"),
                rs.getString("login"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getDate("dateofbirth"),
                rs.getString("password"),
                null);
    }
}
