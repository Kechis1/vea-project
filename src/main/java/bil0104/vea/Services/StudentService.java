package bil0104.vea.Services;

import bil0104.vea.DAO.AbstractDao;
import bil0104.vea.JPA.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private AbstractDao<Student> studentDao;


    public StudentService() { }

    public List<Student> getAll() {
        return studentDao.getAll();
    }
}
