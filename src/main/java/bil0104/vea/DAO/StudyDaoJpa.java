package bil0104.vea.DAO;

import bil0104.vea.JPA.Person;
import bil0104.vea.JPA.Student;
import bil0104.vea.JPA.Study;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class StudyDaoJpa implements StudyDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Study> getAll() {
        return em.createQuery("select s from Study s", Study.class).getResultList();
    }

    @Override
    public void insert(Study study) {
        em.createNativeQuery("insert into studies (year, points, student_id, subject_id) VALUES (?,?,?,?)")
                .setParameter(1, study.getYear())
                .setParameter(2, study.getPoints())
                .setParameter(3, study.getStudent().getId())
                .setParameter(4, study.getSubject().getId())
                .executeUpdate();
    }

    @Override
    public List<Study> findByStudentAndYear(Person person, String year) {
        return em.createQuery("select st from Study st join Subject su on st.subject = su where st.student = :student and st.year = :year", Study.class)
                .setParameter("student", person)
                .setParameter("year", year)
                .getResultList();
    }

    @Override
    public Study find(long id) {
        return em.createQuery("select st from Study st where st.id = :id", Study.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void delete(long id) {
        Study study = find(id);
        em.remove(study);
    }

    @Override
    public void update(Study study) {
        em.merge(study);
    }
}
