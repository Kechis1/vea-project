package bil0104.vea.Services;

import bil0104.vea.DAO.StudyDao;
import bil0104.vea.JPA.Person;
import bil0104.vea.JPA.Study;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudyService {

    @Autowired
    private StudyDao studyDao;

    public void insert(Study study) {
        studyDao.insert(study);
    }

    public List<Study> findByStudentAndYear(Person person, String year) {
        return studyDao.findByStudentAndYear(person, year);
    }
}