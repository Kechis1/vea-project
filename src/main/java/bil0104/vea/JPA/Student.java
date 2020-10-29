package bil0104.vea.JPA;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    @OneToMany(mappedBy = "student")
    private List<Study> studies;

    public Student() {
    }

    public Student(long id, String login, String firstName, String lastName, Date dateOfBirth, List<Study> studies) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.studies = studies;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", studies=" + studies +
                '}';
    }
}
