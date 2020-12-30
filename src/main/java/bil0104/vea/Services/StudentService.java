package bil0104.vea.Services;

import bil0104.vea.DAO.StudentDao;
import bil0104.vea.JPA.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public Student findByLogin(String login) {
        return studentDao.findByLogin(login);
    }

    public List<Student> getAll() {
        return studentDao.getAll();
    }

    public void delete(long id) {
        studentDao.delete(id);
    }

    public void insert(Student student) {
        studentDao.insert(student);
    }
}
