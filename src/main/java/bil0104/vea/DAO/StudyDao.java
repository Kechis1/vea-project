package bil0104.vea.DAO;

import bil0104.vea.JPA.Person;
import bil0104.vea.JPA.Study;

import java.util.List;


public interface StudyDao {

    void insert(Study study);
    List<Study> getAll();

    List<Study> findByStudentAndYear(Person person, String year);
    void delete(long id);

    Study find(long id);

    void update(Study study);
}
