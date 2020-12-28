package bil0104.vea.JPA;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="Subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String abbreviation;
    @NotNull
    private String name;
    @NotNull
    private int year;
    @NotNull
    public Semester semester;
    @NotNull
    private int credits;
    @OneToMany
    public List<Study> studies;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="teacher_id")
    public Teacher teacher;

    public Subject() {
    }

    public Subject(long id, @NotNull String abbreviation, @NotNull String name, @NotNull int year, @NotNull Semester semester, @NotNull int credits, List<Study> studies, Teacher teacher) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
        this.year = year;
        this.semester = semester;
        this.credits = credits;
        this.studies = studies;
        this.teacher = teacher;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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
                ", abbreviation='" + abbreviation + '\'' +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", semester=" + semester +
                ", credits=" + credits +
                ", studies=" + studies +
                ", teacher=" + teacher +
                '}';
    }
}