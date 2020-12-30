package bil0104.vea.DAO;

import bil0104.vea.JPA.Person;
import bil0104.vea.JPA.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class PersonDaoJpa implements PersonDao<Person> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Person insert(Person person) {
        em.persist(person);
        em.flush();
        return person;
    }

    @Override
    public List<Person> getAll() {
        return em.createQuery("select t from Person t", Person.class).getResultList();
    }

    @Override
    public Person findById(long id) {
        return em.find(Person.class, id);
    }

    @Override
    public Person update(Person person) {
        em.merge(person);
        em.flush();
        return person;
    }

    @Override
    public void delete(long id) {
        Person person = em.find(Person.class, id);
        em.remove(person);
    }

    public Person findByLogin(String login) {
        try {
            return em.createQuery("select t from Person t where t.login=:login", Person.class).setParameter("login", login).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
