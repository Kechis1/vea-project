package bil0104.vea.DAO;

import bil0104.vea.JPA.Teacher;

public interface TeacherDao extends AbstractDao<Teacher> {
    Teacher findByLogin(String login);
}
