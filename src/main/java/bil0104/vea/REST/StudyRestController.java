package bil0104.vea.REST;

import bil0104.vea.Entities.Study;
import bil0104.vea.Services.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudyRestController {
    @Autowired
    private StudyService studyService;

    @RequestMapping(path = "/api/studies", method = RequestMethod.GET)
    public List<Study> getAll() {
        return studyService.getAll();
    }

    @RequestMapping(path = "/api/studies/{id}", method = RequestMethod.GET)
    public Study find(@PathVariable long id) {
        return studyService.findById(id);
    }

    @RequestMapping(path = "/api/studies/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        studyService.delete(id);
    }
}
