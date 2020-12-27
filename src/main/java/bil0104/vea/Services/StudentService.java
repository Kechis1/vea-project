package bil0104.vea.Services;

import bil0104.vea.DAO.AbstractDao;
import bil0104.vea.JPA.Student;
import bil0104.vea.JPA.Study;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private AbstractDao<Student> studentDao;

    private List<Student> students = new ArrayList<>();


    public StudentService() {
        this.init();
    }

    private void init() {
        students.add(new Student(1, "BIL0104", "Daniel", "Bill", new Date(70, Calendar.JANUARY,4), null));
        students.add(new Student(2, "VYR0104", "Ivan", "Vyrojil", new Date(77, Calendar.DECEMBER,5), null));
        students.add(new Student(3, "KAL0104", "Karel", "Kalic", new Date(80, Calendar.JULY,6), null));
        students.add(new Student(4, "MON0104", "Monika", "Monka", new Date(99, Calendar.MARCH,14), null));
        students.add(new Student(5, "BAL0104", "Adriana", "Balicova", new Date(78, Calendar.MAY,23), null));
    }

    public List<Student> getAll() {
        return students;
        // return studentDao.getAll();
    }

    public void delete(long id) {
        Student found = students.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (found == null) {
            return;
        }
        students.remove(found);
    }

    public void insert(Student student) {
        students.add(student);
    }
}
