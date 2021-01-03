package bil0104.vea.DAO.JDBC.Mappers;

import bil0104.vea.Entities.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.jdbc.core.RowMapper;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class StudyMapper implements RowMapper<Study> {
    @Override
    public Study mapRow(ResultSet rs, int rowNum) throws SQLException {
        Study st = new Study(rs.getLong("st_id"),
                rs.getString("st_year"),
                rs.getInt("st_points"),
                new Student(rs.getLong("stu_id"),
                        rs.getString("stu_login"),
                        rs.getString("stu_firstname"),
                        rs.getString("stu_lastname"),
                        rs.getDate("stu_dateofbirth"),
                        null,
                        null,
                        rs.getInt("stu_year")),
                new Subject(rs.getLong("sub_id"),
                        rs.getString("sub_abbreviation"),
                        rs.getString("sub_name"),
                        rs.getInt("sub_year"),
                        Semester.valueOf(rs.getString("sub_semester")),
                        rs.getInt("sub_credits"),
                        null,
                        null));
        if (isThere(rs, "t_id") && rs.getLong("t_id") != 0) {
            st.getSubject().setTeacher(new Teacher(rs.getLong("t_id"),
                null,
                rs.getString("t_firstname"),
                rs.getString("t_lastname"),
                null,
                rs.getString("t_password"),
                null));
        }

        st.setStudentId(rs.getInt("student_id"));
        st.setSubjectId(rs.getInt("subject_id"));
        return st;
    }

    private boolean isThere(ResultSet rs, String column) {
        try {
            rs.findColumn(column);
            return true;
        } catch (SQLException ignored) {

        }

        return false;
    }
}