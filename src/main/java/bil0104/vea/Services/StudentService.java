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


    public StudentService() { }

    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "BIL0104", "Daniel", "Bill", new Date(70, Calendar.JANUARY,4), null));
        students.add(new Student(2, "VYR0104", "Ivan", "Vyrojil", new Date(77, Calendar.DECEMBER,5), null));
        students.add(new Student(3, "KAL0104", "Karel", "Kalic", new Date(80, Calendar.JULY,6), null));
        students.add(new Student(4, "MON0104", "Monika", "Monka", new Date(99, Calendar.MARCH,14), null));
        students.add(new Student(5, "BAL0104", "Adriana", "Balicova", new Date(78, Calendar.MAY,23), null));

        return students;
        // return studentDao.getAll();
    }
}
