package bil0104.vea.Services;

import bil0104.vea.DAO.AbstractDao;
import bil0104.vea.DAO.StudentDao;
import bil0104.vea.DAO.StudyDao;
import bil0104.vea.JPA.Student;
import bil0104.vea.JPA.Study;
import bil0104.vea.JPA.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
@Transactional
public class StudyService {


    @Autowired
    private StudyDao studyDao;


    public void insert(Study study) {
        studyDao.insert(study);
    }

}
