package bil0104.vea.JPA;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Teacher extends Person {
    @OneToMany
    public List<Subject> teaches;

    public Teacher() {
        super();
    }

    public Teacher(long id, String login, @NotNull String firstName, @NotNull String lastName, Date dateOfBirth, @NotNull @Length(min = 5) String password, List<Subject> teaches) {
        super(id, login, firstName, lastName, dateOfBirth, password, Role.TEACHER);
        this.teaches = teaches;
    }

    public Teacher(String login, @NotNull String firstName, @NotNull String lastName, Date dateOfBirth, @NotNull @Length(min = 5) String password, List<Subject> teaches) {
        super(login, firstName, lastName, dateOfBirth, password, Role.TEACHER);
        this.teaches = teaches;
    }

    public List<Subject> getTeaches() {
        return teaches;
    }

    public void setTeaches(List<Subject> teaches) {
        this.teaches = teaches;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "teaches=" + teaches +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
