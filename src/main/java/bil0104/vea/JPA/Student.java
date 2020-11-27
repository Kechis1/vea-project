package bil0104.vea.JPA;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Student")
public class Student extends Person {
    @OneToMany(mappedBy = "Student")
    public List<Study> studies;

    public Student() {
        super();
    }

    public Student(long id, String login, String firstName, String lastName, Date dateOfBirth, List<Study> studies) {
        super(id, login, firstName, lastName, dateOfBirth);
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
                '}';
    }
}
