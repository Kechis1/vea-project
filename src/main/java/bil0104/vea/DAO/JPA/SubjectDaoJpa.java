/*
package bil0104.vea.DAO.JPA;

import bil0104.vea.DAO.SubjectDao;
import bil0104.vea.Entities.Subject;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class SubjectDaoJpa implements SubjectDao {
   
    @PersistenceContext
    private EntityManager em;

    @Override
    public Subject insert(Subject subject) {
        em.persist(subject);
        em.flush();
        return subject;
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
    public Subject update(Subject subject) {
        em.merge(subject);
        em.flush();
        return subject;
    }

    @Override
    public void delete(long id) {
        Subject subject = em.find(Subject.class, id);
        em.remove(subject);
    }

    @Override
    public void delete(Subject subject) {
        em.remove(subject);
    }

    @Override
    public List<Subject> getWithoutStudent(long id) {
        return em.createQuery("select s from Subject s where s.id not in (select st.subject.id from Study st where st.student.id = :studentId)", Subject.class)
                .setParameter("studentId", id)
                .getResultList();
    }

    @Override
    public List<Subject> getWithoutTeacher() {
        return em.createQuery("select s from Subject s where s.teacher is null", Subject.class)
                .getResultList();
    }

    @Override
    public void detachTeacher(long id) {
        em.createQuery("update Subject s set s.teacher = null where s.teacher.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
*/
