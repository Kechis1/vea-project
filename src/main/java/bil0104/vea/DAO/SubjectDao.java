package bil0104.vea.DAO;

import bil0104.vea.JPA.Subject;

import java.util.List;

public interface SubjectDao extends AbstractDao<Subject> {

    List<Subject> getWithoutStudent(long id);
}