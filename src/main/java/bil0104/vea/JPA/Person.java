package bil0104.vea.JPA;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.Normalizer;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements UserDetails {
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
    @NotNull
    @Length(min = 5)
    protected String password;
    protected Role role;

    public Person() {
    }

    public Person(long id, String login, @NotNull String firstName, @NotNull String lastName, Date dateOfBirth, @NotNull @Length(min = 5) String password, Role role) {
        this.id = id;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
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
