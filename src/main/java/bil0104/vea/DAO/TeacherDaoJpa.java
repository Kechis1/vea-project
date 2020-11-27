package bil0104.vea.DAO;

import bil0104.vea.JPA.Teacher;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class TeacherDaoJpa implements AbstractDao<Teacher> {
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void postConstructor() {

    }

    public void insert(Teacher teacher) {
        em.persist(teacher);
    }

    public List<Teacher> list() {
        return em.createQuery("select t from Teacher t", Teacher.class).getResultList();
    }

    public Teacher findById(long id) {
        return em.find(Teacher.class, id);
    }

    public void update(Teacher teacher) {

    }

    public void delete(long id) {

    }
}
