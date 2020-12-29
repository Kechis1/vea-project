package bil0104.vea.Services;

import bil0104.vea.DAO.AbstractDao;
import bil0104.vea.JPA.Semester;
import bil0104.vea.JPA.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private AbstractDao<Subject> subjectDao;

    public SubjectService() { }

    @PostConstruct
    public void init() {
        this.insert(new Subject(1, "UDBS", "Úvod do dbs", 2, Semester.SUMMER, 4, null, null));
        this.insert(new Subject(2, "AVD", "Algority vykonání dotazů", 5, Semester.SUMMER, 5, null, null));
        this.insert(new Subject(3, "ADBS", "Administrace DBS", 3, Semester.WINTER, 3, null, null));
    }

    public List<Subject> getAll() {
        return subjectDao.getAll();
    }

    public void delete(long id) {
        subjectDao.delete(id);
    }

    public void insert(Subject subject) {
        subjectDao.insert(subject);
    }
}
