package bil0104.vea.REST;

import bil0104.vea.Entities.Person;
import bil0104.vea.Entities.Role;
import bil0104.vea.Entities.Student;
import bil0104.vea.Entities.Teacher;
import bil0104.vea.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static bil0104.vea.Utils.EncryptedPasswordUtils.encryptPassword;

@RestController
public class TeacherRestController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(path = "/api/teachers", method = RequestMethod.GET)
    public List<Teacher> getAll() {
        return teacherService.getAll();
    }

    @RequestMapping(path = "/api/teachers/{id}", method = RequestMethod.GET)
    public Teacher find(@PathVariable long id) {
        return teacherService.findById(id);
    }

    @RequestMapping(path = "/api/teachers", method = RequestMethod.POST)
    public Teacher create(@RequestBody Teacher teacher) {
        teacher.setLogin(Person.findNextLogin(teacher, teacherService::findByLogin));
        teacher.setRole(Role.TEACHER);
        teacher.setPassword(encryptPassword(teacher.getPassword()));
        return teacherService.insert(teacher);
    }

    @RequestMapping(path = "/api/teachers/{id}", method = RequestMethod.PUT)
    public void edit(@RequestBody Teacher teacher, @PathVariable long id) {
        Teacher st = teacherService.findById(id);
        st.setFirstName(teacher.getFirstName());
        st.setLastName(teacher.getLastName());
        st.setDateOfBirth(teacher.getDateOfBirth());
        teacherService.update(st);
    }

    @RequestMapping(path = "/api/teachers/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        teacherService.delete(id);
    }
}
