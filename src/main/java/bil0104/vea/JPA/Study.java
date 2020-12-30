package bil0104.vea.JPA;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "studies")
@IdClass(StudyPrimaryKey.class)
public class Study {
    @Id
    private String year;
    @Min(0)
    @Max(100)
    private int points;

    @ManyToOne
    @Id
    @JoinColumn(name = "student_id")
    public Student student;
    @ManyToOne
    @Id
    @JoinColumn(name = "subject_id")
    public Subject subject;

    public Study() {
    }

    public Study(String year, @Min(0) @Max(100) int points, Student student, Subject subject) {
        this.year = year;
        this.points = points;
        this.student = student;
        this.subject = subject;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        student.studies.add(this);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        subject.studies.add(this);
    }

    @Override
    public String toString() {
        return "Study{" +
                " year=" + year +
                ", points=" + points +
                ", student=" + student.getLogin() +
                ", subject=" + subject.getAbbreviation() +
                '}';
    }
}