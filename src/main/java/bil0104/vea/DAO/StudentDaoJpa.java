package bil0104.vea.DAO;

import bil0104.vea.JPA.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class StudentDaoJpa implements StudentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Student student) {
        em.persist(student);
    }

    @Override
    public List<Student> getAll() {
        return em.createQuery("select t from Student t", Student.class).getResultList();
    }

    @Override
    public Student findById(long id) {
        return em.find(Student.class, id);
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(long id) {
        Student student = em.find(Student.class, id);
        em.remove(student);
    }

    @Override
    public Student findByLogin(String login) {
        try {
            return em.createQuery("select t from Student t where t.login=:login", Student.class).setParameter("login", login).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
