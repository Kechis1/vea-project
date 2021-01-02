package bil0104.vea.Entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.Normalizer;
import java.util.Date;
import java.util.function.Function;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String login;
    @NotNull
    protected String firstName;
    @NotNull
    protected String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date dateOfBirth;
    @Column(name = "password", length = 128, nullable = false)
    protected String password;
    protected Role role;

    public Person() {
    }

    public Person(long id, String login, @NotNull String firstName, @NotNull String lastName, Date dateOfBirth, @NotNull String password, Role role) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.role = role;
    }

    public Person(String login, @NotNull String firstName, @NotNull String lastName, Date dateOfBirth, @NotNull String password, Role role) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
