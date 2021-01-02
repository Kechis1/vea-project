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

    public void delete(long id) {
        studyDao.delete(id);
    }

    public Study find(long id) {
        return studyDao.find(id);
    }

    public void update(Study study) {
        studyDao.update(study);
    }

    public Study findByUniqueKey(long studentId, long subjectId, String year) {
        return studyDao.findByUniqueKey(studentId, subjectId, year);
    }

    public List<Study> getAll() {
        return studyDao.getAll();
    }

    public void deleteWhereSubjectId(long id) {
        studyDao.deleteWhereSubjectId(id);
    }

    public void deleteWhereStudentId(long id) {
        studyDao.deleteWhereStudentId(id);
    }
}
