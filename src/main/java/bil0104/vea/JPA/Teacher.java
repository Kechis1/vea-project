package bil0104.vea.JPA;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Teacher extends Person {
    @OneToMany
    public List<Subject> teaches;

    public Teacher() {
        super();
    }

    public Teacher(long id, String login, String firstName, String lastName, Date dateOfBirth, List<Subject> teaches) {
        super(id, login, firstName, lastName, dateOfBirth);
        this.teaches = teaches;
    }

    public List<Subject> getTeaches() {
        return teaches;
    }

    public void setTeaches(List<Subject> teaches) {
        this.teaches = teaches;
    }

    @Override
    public String toString() {
        return "Student{" +
                "teaches=" + teaches +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
