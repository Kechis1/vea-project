package bil0104.vea.Services;

import bil0104.vea.DAO.PersonDao;
import bil0104.vea.JPA.Person;
import bil0104.vea.JPA.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonDao<Person> personDao;

    public PersonService() {
    }


    @PostConstruct
    public void init() {
        this.insert(new Person("ADM000", "Admin", "Admin", new Date(80, Calendar.APRIL, 2), "$2a$10$SAiB6hw6yWnPpAp82L9OqeHGQU9KjSKAKLRBgYAuysJe4pF25I4Gy", Role.ADMIN));
    }

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
}
