package bil0104.vea.DAO;

import bil0104.vea.JPA.Subject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class SubjectDaoJpa implements AbstractDao<Subject> {
   
    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Subject subject) {
        em.persist(subject);
    }

    @Override
    public List<Subject> getAll() {
        return em.createQuery("select t from Subject t", Subject.class).getResultList();
    }

    @Override
    public Subject findById(long id) {
        return em.find(Subject.class, id);
    }

    @Override
    public void update(Subject subject) {

    }

    @Override
    public void delete(long id) {
        Subject subject = em.find(Subject.class, id);
        em.remove(subject);
    }

}
