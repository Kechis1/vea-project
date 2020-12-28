package bil0104.vea.JPA;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Student extends Person {
    @OneToMany
    public List<Study> studies;

    public Student() {
        super();
    }

    public Student(long id, String login, @NotNull String firstName, @NotNull String lastName, Date dateOfBirth, @NotNull @Length(min = 5) String password, List<Study> studies) {
        super(id, login, firstName, lastName, dateOfBirth, password, Role.STUDENT);
        this.studies = studies;
    }

    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studies=" + studies +
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
