package bil0104.vea.JPA;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "teacher_id", insertable = false, updatable = false)
    private long teacherId;
    private String name;
    private String year;
    public Semester semester;
    private int credits;
    @OneToMany
    public List<Study> studies;
    @ManyToOne
    public Teacher teacher;

    public Subject() {
    }

    public Subject(long id, String name, String year, Semester semester, int credits, List<Study> studies, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.semester = semester;
        this.credits = credits;
        this.studies = studies;
        this.teacher = teacher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }


    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", semester=" + semester +
                ", credits=" + credits +
                ", studies=" + studies +
                ", teacher=" + teacher +
                '}';
    }
}