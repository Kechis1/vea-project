package bil0104.vea.Services;

import bil0104.vea.DAO.TeacherDao;
import bil0104.vea.JPA.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    public TeacherService() { }

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
