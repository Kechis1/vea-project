package bil0104.vea.Services;

import bil0104.vea.DAO.AbstractDao;
import bil0104.vea.JPA.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SubjectService {

    @Autowired
    private AbstractDao<Subject> subjectDao;

    public SubjectService() { }

    public List<Subject> getAll() {
        return subjectDao.getAll();
    }

    public void delete(long id) {
        subjectDao.delete(id);
    }

    public void insert(Subject subject) {
        subjectDao.insert(subject);
    }

    public Subject findById(long id) {
        return subjectDao.findById(id);
    }
}
