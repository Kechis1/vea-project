package bil0104.vea.DAO;

import bil0104.vea.Entities.Person;
import bil0104.vea.Entities.Study;
import bil0104.vea.Entities.Subject;

import java.util.List;


public interface StudyDao extends AbstractDao<Study> {
    List<Study> findByStudentAndYear(Person person, String year);

    Study findByUniqueKey(long studentId, long subjectId, String year);

    void deleteWhereSubjectId(long id);

    void deleteWhereStudentId(long id);

    List<Study> findBySubjectAndYear(Subject subject, String year);
}
