package bil0104.vea.DAO.JDBC.Mappers;

import bil0104.vea.Entities.Semester;
import bil0104.vea.Entities.Student;
import bil0104.vea.Entities.Study;
import bil0104.vea.Entities.Subject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudyMapper implements RowMapper<Study> {
    @Override
    public Study mapRow(ResultSet rs, int rowNum) throws SQLException {
        Study st = new Study(rs.getLong("st.id"),
                rs.getString("st.year"),
                rs.getInt("st.points"),
                new Student(rs.getLong("stu.id"),
                        rs.getString("stu.login"),
                        rs.getString("stu.firstname"),
                        rs.getString("stu.lastname"),
                        rs.getDate("stu.dateofbirth"),
                        rs.getString("stu.password"),
                        null,
                        rs.getInt("stu.year")),
                new Subject(rs.getLong("sub.id"),
                        rs.getString("sub.abbreviation"),
                        rs.getString("sub.name"),
                        rs.getInt("sub.year"),
                        Semester.valueOf(rs.getString("sub.semester")),
                        rs.getInt("sub.credits"),
                        null,
                        null));
        st.setStudentId(rs.getInt("st.student_id"));
        st.setSubjectId(rs.getInt("st.subject_id"));
        return st;
    }
}