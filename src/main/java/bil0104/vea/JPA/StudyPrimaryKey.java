package bil0104.vea.JPA;

import java.io.Serializable;
import java.util.Objects;

public class StudyPrimaryKey implements Serializable {
    public Student student;
    public Subject subject;
    public String year;

    public StudyPrimaryKey() {
    }

    public StudyPrimaryKey(Student student, Subject subject, String year) {
        this.student = student;
        this.subject = subject;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyPrimaryKey that = (StudyPrimaryKey) o;
        return student == that.student &&
                subject == that.subject &&
                year.equals(that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, subject, year);
    }

    @Override
    public String toString() {
        return "StudyPrimaryKey{" +
                "student=" + student +
                ", subject=" + subject +
                ", year=" + year +
                '}';
    }
}
