package bil0104.vea.DAO;

import bil0104.vea.JPA.Teacher;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class TeacherDaoJpa implements TeacherDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Teacher insert(Teacher teacher) {
        em.persist(teacher);
        em.flush();
        return teacher;
    }

    @Override
    public List<Teacher> getAll() {
        return em.createQuery("select t from Teacher t", Teacher.class).getResultList();
    }

    @Override
    public Teacher findById(long id) {
        return em.find(Teacher.class, id);
    }

    @Override
    public Teacher update(Teacher teacher) {
        em.merge(teacher);
        em.flush();
        return teacher;
    }

    @Override
    public void delete(long id) {
        Teacher teacher = em.find(Teacher.class, id);
        em.remove(teacher);
    }

    @Override
    public Teacher findByLogin(String login) {
        try {
            return em.createQuery("select t from Teacher t where t.login=:login", Teacher.class).setParameter("login", login).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
