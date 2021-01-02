package bil0104.vea.Services;

import bil0104.vea.DAO.TeacherDao;
import bil0104.vea.Entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

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

    public Teacher findById(long id) {
        return teacherDao.findById(id);
    }

    public void update(Teacher teacher) {
        teacherDao.update(teacher);
    }
}
