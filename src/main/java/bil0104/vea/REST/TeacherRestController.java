package bil0104.vea.REST;

import bil0104.vea.Entities.Teacher;
import bil0104.vea.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @RequestMapping(path = "/api/teachers/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        teacherService.delete(id);
    }
}
