package bil0104.vea.REST;

import bil0104.vea.Entities.Subject;
import bil0104.vea.Services.SubjectService;
import bil0104.vea.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectRestController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping(path = "/api/subjects", method = RequestMethod.GET)
    public List<Subject> getAll() {
        return subjectService.getAll();
    }

    @RequestMapping(path = "/api/subjects/{id}", method = RequestMethod.GET)
    public Subject find(@PathVariable long id) {
        return subjectService.findById(id);
    }

    @RequestMapping(path = "/api/subjects", method = RequestMethod.POST)
    public Subject create(@RequestBody Subject subject) {
        if (subject.getTeacherId() != 0) {
            subject.setTeacher(teacherService.findById(subject.getTeacherId()));
        }
        return subjectService.insert(subject);
    }

    @RequestMapping(path = "/api/subjects/{id}", method = RequestMethod.PUT)
    public void edit(@RequestBody Subject subject, @PathVariable long id) {
        Subject st = subjectService.findById(id);
        st.setAbbreviation(subject.getAbbreviation());
        st.setName(subject.getName());
        st.setSemester(subject.getSemester());
        st.setYear(subject.getYear());
        st.setCredits(subject.getCredits());
        if (subject.getTeacherId() != 0) {
            st.setTeacher(teacherService.findById(subject.getTeacherId()));
        } else {
            st.setTeacher(null);
        }
        subjectService.update(st);
    }

    @RequestMapping(path = "/api/subjects/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        subjectService.delete(id);
    }
}
