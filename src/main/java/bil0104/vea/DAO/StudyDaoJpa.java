package bil0104.vea.DAO;

import bil0104.vea.JPA.Study;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class StudyDaoJpa implements StudyDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void insert(Study study) {
        em.createNativeQuery("insert into studies (year, points, student_id, subject_id) VALUES (?,?,?,?)")
                .setParameter(1, study.getYear())
                .setParameter(2, study.getPoints())
                .setParameter(3, study.getStudent().getId())
                .setParameter(4, study.getSubject().getId())
                .executeUpdate();
        // em.persist(study);
    }


}
