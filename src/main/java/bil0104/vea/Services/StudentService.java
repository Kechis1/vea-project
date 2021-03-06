package bil0104.vea.Services;

import bil0104.vea.DAO.StudentDao;
import bil0104.vea.Entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Student insert(Student student) {
        return studentDao.insert(student);
    }

    public Student findById(long id) {
        return studentDao.findById(id);
    }

    public void update(Student student) {
        studentDao.update(student);
    }
}
