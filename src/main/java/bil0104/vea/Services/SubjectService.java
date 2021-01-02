package bil0104.vea.Services;

import bil0104.vea.DAO.SubjectDao;
import bil0104.vea.JPA.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    public SubjectService() { }

    public List<Subject> getAll() {
        return subjectDao.getAll();
    }

    public void delete(long id) {
        subjectDao.delete(id);
    }

    public void delete(Subject subject) {
        subjectDao.delete(subject);
    }

    public void insert(Subject subject) {
        subjectDao.insert(subject);
    }

    public Subject findById(long id) {
        return subjectDao.findById(id);
    }

    public List<Subject> getWithoutStudent(long id) {
        return subjectDao.getWithoutStudent(id);
    }

    public List<Subject> getWithoutTeacher() {
        return subjectDao.getWithoutTeacher();
    }

    public void update(Subject subject) {
        subjectDao.update(subject);
    }

    public void detachTeacher(long id) {
        subjectDao.detachTeacher(id);
    }
}
