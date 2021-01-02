package bil0104.vea.Entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
//@Table(name = "students")
public class Student extends Person {
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    public List<Study> studies;
    public int year;

    public Student() {
        super();
    }

    public Student(long id, String login, @NotNull String firstName, @NotNull String lastName, Date dateOfBirth, @NotNull @Length(min = 5) String password, List<Study> studies, int year) {
        super(id, login, firstName, lastName, dateOfBirth, password, Role.STUDENT);
        this.year = year;
        this.studies = studies;
    }

    public Student(String login, @NotNull String firstName, @NotNull String lastName, Date dateOfBirth, @NotNull @Length(min = 5) String password, List<Study> studies, int year) {
        super(login, firstName, lastName, dateOfBirth, password, Role.STUDENT);
        this.year = year;
        this.studies = studies;
    }

    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(List<Study> studies) {
        this.studies = studies;

        for (Study s : studies) {
            s.student = this;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Student{" +
                " id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", year=" + year +
                ", studies=" + studies +
                '}';
    }
}
