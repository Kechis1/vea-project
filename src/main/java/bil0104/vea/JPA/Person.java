package bil0104.vea.JPA;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.Normalizer;
import java.util.Date;
import java.util.function.Function;

@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String login;
    @NotNull
    protected String firstName;
    @NotNull
    protected String lastName;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    protected Date dateOfBirth;

    public Person() {
    }

    public Person(long id, String login, String firstName, String lastName, Date dateOfBirth) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static String findNextLogin(Person person, Function<String, Person> fn) {
        int i = 0;
        String login;
        String pre = Normalizer.normalize(person.getLastName().length() > 3 ?
                person.getLastName().substring(0, 3) :
                person.getLastName(), Normalizer.Form.NFD
        ).replaceAll("\\p{M}", "")
                .replaceAll("\\s", "")
                .toUpperCase();
        while (true) {
            login = pre + String.format("%03d", i);
            if (fn.apply(login) == null) {
                return login;
            }
            i++;
        }
    }
}
