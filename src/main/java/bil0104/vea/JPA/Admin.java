package bil0104.vea.JPA;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Admin extends Person {

    public Admin() {
        super();
    }

    public Admin(long id, String login, @NotNull String firstName, @NotNull String lastName, Date dateOfBirth, @NotNull @Length(min = 5) String password) {
        super(id, login, firstName, lastName, dateOfBirth, password, Role.ADMIN);
    }

    public Admin(String login, @NotNull String firstName, @NotNull String lastName, Date dateOfBirth, @NotNull @Length(min = 5) String password) {
        super(login, firstName, lastName, dateOfBirth, password, Role.ADMIN);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
