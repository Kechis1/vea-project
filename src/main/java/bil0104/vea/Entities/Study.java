package bil0104.vea.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
/*@Table(name = "studies", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"year", "student_id", "subject_id"}, name = "study_uk")
})*/
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String year;
    @Min(0)
    @Max(100)
    private int points;

    @ManyToOne
    @JoinColumn(name = "student_id")
    public Student student;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    public Subject subject;

    @JsonBackReference
    @Transient
    public long studentId;
    @Transient
    @JsonBackReference
    public long subjectId;

    public Study() {
    }

    public Study(String year, @Min(0) @Max(100) int points, Student student, Subject subject) {
        this.year = year;
        this.points = points;
        this.student = student;
        this.subject = subject;
    }

    public Study(String year, @Min(0) @Max(100) int points) {
        this.year = year;
        this.points = points;
    }

    public Study(long id, String year, @Min(0) @Max(100) int points, Student student, Subject subject) {
        this.id = id;
        this.year = year;
        this.points = points;
        this.student = student;
        this.subject = subject;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void addSubject(Subject subject) {
        subject.studies.add(this);
    }

    @Override
    public String toString() {
        return "Study{" +
                " year=" + year +
                ", points=" + points +
                ", student=" + (student == null ? "null" : student.getLogin())+
                ", subject=" + (subject == null ? "null" : subject.getAbbreviation())+
                '}';
    }
}