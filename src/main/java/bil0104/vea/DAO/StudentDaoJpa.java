package bil0104.vea.DAO;

import bil0104.vea.JPA.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class StudentDaoJpa implements AbstractDao<Student> {
   
    @PersistenceContext
    private EntityManager em;

    public void insert(Student student) {
        em.persist(student);
    }

    public List<Student> getAll() {
        return em.createQuery("select t from Student t", Student.class).getResultList();
    }

    public Student findById(long id) {
        return em.find(Student.class, id);
    }

    public void update(Student student) {

    }

    public void delete(long id) {

    }
}
