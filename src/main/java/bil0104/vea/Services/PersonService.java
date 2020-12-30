package bil0104.vea.Services;

import bil0104.vea.DAO.PersonDao;
import bil0104.vea.JPA.Person;
import bil0104.vea.JPA.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PersonService implements UserDetailsService {

    @Autowired
    private PersonDao<Person> personDao;

    public Person findByLogin(String login) {
        return personDao.findByLogin(login);
    }

    public List<Person> getAll() {
        return personDao.getAll();
    }

    public void delete(long id) {
        personDao.delete(id);
    }

    public void insert(Person person) {
        personDao.insert(person);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Person appUser = this.personDao.findByLogin(userName);

        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + appUser);

        return User.withUsername(appUser.getLogin())
                .password(appUser.getPassword())
                .roles(appUser.getRole().toString())
                .build();
    }
}
