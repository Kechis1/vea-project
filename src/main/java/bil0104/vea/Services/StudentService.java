package bil0104.vea.Services;

import bil0104.vea.DAO.StudentDao;
import bil0104.vea.JPA.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public StudentService() { }


    @PostConstruct
    public void init() {
        this.insert(new Student("BIL104", "Daniel", "Bill", new Date(1999, Calendar.APRIL,2), "$2a$10$SAiB6hw6yWnPpAp82L9OqeHGQU9KjSKAKLRBgYAuysJe4pF25I4Gy", null, 3));
        this.insert(new Student("ALE201", "Alena", "Aleso", new Date(1998, Calendar.MARCH,5), "$2a$10$SAiB6hw6yWnPpAp82L9OqeHGQU9KjSKAKLRBgYAuysJe4pF25I4Gy", null, 4));
        this.insert(new Student( "DRA001", "Bronze", "Drag", new Date(1997, Calendar.JANUARY,11), "$2a$10$SAiB6hw6yWnPpAp82L9OqeHGQU9KjSKAKLRBgYAuysJe4pF25I4Gy", null, 5));
    }

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
