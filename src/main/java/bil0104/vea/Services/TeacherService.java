package bil0104.vea.Services;

import bil0104.vea.DAO.TeacherDao;
import bil0104.vea.JPA.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    public TeacherService() { }

    @PostConstruct
    public void init() {
        this.insert(new Teacher("DUB000", "Lukáš", "Denver", new Date(92, Calendar.FEBRUARY,9), "$2a$10$SAiB6hw6yWnPpAp82L9OqeHGQU9KjSKAKLRBgYAuysJe4pF25I4Gy", null));
        this.insert(new Teacher( "BRE123", "Alžběta", "Helsinki", new Date(91, Calendar.DECEMBER,8), "$2a$10$SAiB6hw6yWnPpAp82L9OqeHGQU9KjSKAKLRBgYAuysJe4pF25I4Gy", null));
        this.insert(new Teacher( "ZEN003", "Eliška", "Tokyo", new Date(90, Calendar.JANUARY,10), "$2a$10$SAiB6hw6yWnPpAp82L9OqeHGQU9KjSKAKLRBgYAuysJe4pF25I4Gy", null));
    }

    public Teacher findByLogin(String login) {
        return teacherDao.findByLogin(login);
    }

    public List<Teacher> getAll() {
        return teacherDao.getAll();
    }

    public void delete(long id) {
        teacherDao.delete(id);
    }

    public void insert(Teacher teacher) {
        teacherDao.insert(teacher);
    }
}
