package bil0104.vea.REST;

import bil0104.vea.Entities.Student;
import bil0104.vea.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Student create() {

        return null;
    }

    @RequestMapping(path = "/api/students/{id}", method = RequestMethod.PUT)
    public void edit(@PathVariable long id) {

    }

    @RequestMapping(path = "/api/students/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        studentService.delete(id);
    }
}
