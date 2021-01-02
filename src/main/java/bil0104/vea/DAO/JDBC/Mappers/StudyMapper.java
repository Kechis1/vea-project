package bil0104.vea.DAO.JDBC.Mappers;

import bil0104.vea.Entities.Study;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudyMapper implements RowMapper<Study> {
    @Override
    public Study mapRow(ResultSet rs, int rowNum) throws SQLException {
        Study st = new Study(rs.getLong("id"),
                rs.getString("year"),
                rs.getInt("points"),
                null,
                null);
        st.setStudentId(rs.getInt("student_id"));
        st.setSubjectId(rs.getInt("subject_id"));
        return st;
    }
}