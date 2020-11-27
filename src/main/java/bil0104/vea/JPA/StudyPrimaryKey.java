package bil0104.vea.JPA;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

public class StudyPrimaryKey implements Serializable {
    public long studentId;
    public long subjectId;
    public char year;

    public StudyPrimaryKey() {
    }

    public StudyPrimaryKey(long studentId, long subjectId, char year) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyPrimaryKey that = (StudyPrimaryKey) o;
        return studentId == that.studentId &&
                subjectId == that.subjectId &&
                year == that.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, subjectId, year);
    }

    @Override
    public String toString() {
        return "StudyPrimaryKey{" +
                "studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", year=" + year +
                '}';
    }
}
