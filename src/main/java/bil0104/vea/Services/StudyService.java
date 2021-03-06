package bil0104.vea.Services;

import bil0104.vea.DAO.StudyDao;
import bil0104.vea.Entities.Person;
import bil0104.vea.Entities.Study;
import bil0104.vea.Entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudyService {

    @Autowired
    private StudyDao studyDao;

    public Study insert(Study study) {
        return studyDao.insert(study);
    }

    public List<Study> findByStudentAndYear(Person person, String year) {
        return studyDao.findByStudentAndYear(person, year);
    }

    public void delete(long id) {
        studyDao.delete(id);
    }

    public Study update(Study study) {
        return studyDao.update(study);
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

    public List<Study> findBySubjectAndYear(Subject subject, String year) {
        return studyDao.findBySubjectAndYear(subject, year);
    }

    public Study findById(long id) {
        return studyDao.findById(id);
    }
}
