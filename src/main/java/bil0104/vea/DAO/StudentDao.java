package bil0104.vea.DAO;

import bil0104.vea.JPA.Student;

public interface StudentDao extends AbstractDao<Student> {
    Student findByLogin(String login);
}
