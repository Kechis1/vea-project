package bil0104.vea.REST;

import bil0104.vea.Entities.Person;
import bil0104.vea.Entities.Role;
import bil0104.vea.Entities.Student;
import bil0104.vea.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static bil0104.vea.Utils.EncryptedPasswordUtils.encryptPassword;

@RestController
public class StudentRestController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(path = "/api/students", method = RequestMethod.GET)
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @RequestMapping(path = "/api/students/{id}", method = RequestMethod.GET)
    public Student find(@PathVariable long id) {
        return studentService.findById(id);
    }

    @RequestMapping(path = "/api/students", method = RequestMethod.POST)
    public Student create(@RequestBody Student student) {
        student.setLogin(Person.findNextLogin(student, studentService::findByLogin));
        student.setRole(Role.STUDENT);
        student.setPassword(encryptPassword(student.getPassword()));
        return studentService.insert(student);
    }

    @RequestMapping(path = "/api/students/{id}", method = RequestMethod.PUT)
    public void edit(@RequestBody Student student, @PathVariable long id) {
        Student st = studentService.findById(id);
        st.setFirstName(student.getFirstName());
        st.setLastName(student.getLastName());
        st.setDateOfBirth(student.getDateOfBirth());
        st.setYear(student.getYear());
        studentService.update(st);
    }

    @RequestMapping(path = "/api/students/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        studentService.delete(id);
    }
}
