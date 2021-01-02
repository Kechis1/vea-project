package bil0104.vea.DAO.JDBC.Mappers;

import bil0104.vea.Entities.Semester;
import bil0104.vea.Entities.Subject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements RowMapper<Subject> {
    @Override
    public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
        Subject sub = new Subject(rs.getLong("id"),
                rs.getString("abbreviation"),
                rs.getString("name"),
                rs.getInt("year"),
                Semester.valueOf(rs.getString("semester")),
                rs.getInt("credits"),
                null,
                null);
        sub.setTeacherId(rs.getInt("teacher_id"));
        return sub;
    }
}