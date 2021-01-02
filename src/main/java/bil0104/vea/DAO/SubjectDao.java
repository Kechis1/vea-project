package bil0104.vea.DAO;

import bil0104.vea.Entities.Subject;

import java.util.List;

public interface SubjectDao extends AbstractDao<Subject> {
    List<Subject> getWithoutStudent(long id);
    List<Subject> getWithoutTeacher();
    void delete(Subject subject);
    void detachTeacher(long id);
}
