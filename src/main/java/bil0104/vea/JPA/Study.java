package bil0104.vea.JPA;

import javax.persistence.*;

@Entity
@Table(name="Study")
@IdClass(StudyPrimaryKey.class)
public class Study {
    @Id
    private long subjectId;
    @Id
    private long studentId;
    @Id
    private char year;
    private int points;

    @ManyToOne
    public Student student;
    @ManyToOne
    public Subject subject;

    public Study() {
    }

    public Study(long subjectId, long studentId, char year, int points, Student student, Subject subject) {
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.year = year;
        this.points = points;
        this.student = student;
        this.subject = subject;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public char getYear() {
        return year;
    }

    public void setYear(char year) {
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

    @Override
    public String toString() {
        return "Study{" +
                "subjectId=" + subjectId +
                ", studentId=" + studentId +
                ", year=" + year +
                ", points=" + points +
                ", student=" + student +
                ", subject=" + subject +
                '}';
    }
}