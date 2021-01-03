package bil0104.vea.REST;

import bil0104.vea.Entities.Subject;
import bil0104.vea.Services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectRestController {
    @Autowired
    private SubjectService subjectService;

    @RequestMapping(path = "/api/subjects", method = RequestMethod.GET)
    public List<Subject> getAll() {
        return subjectService.getAll();
    }

    @RequestMapping(path = "/api/subjects/{id}", method = RequestMethod.GET)
    public Subject find(@PathVariable long id) {
        return subjectService.findById(id);
    }

    @RequestMapping(path = "/api/subjects/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        subjectService.delete(id);
    }
}
