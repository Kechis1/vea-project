package bil0104.vea.DAO.JDBC.Mappers;

import bil0104.vea.Entities.Person;
import bil0104.vea.Entities.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Person(rs.getLong("id"),
                rs.getString("login"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getDate("dateofbirth"),
                rs.getString("password"),
                Role.valueOf(rs.getString("role"))
        );
    }
}
