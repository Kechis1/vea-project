package bil0104.vea.Controllers.Web;

import bil0104.vea.Entities.Semester;
import bil0104.vea.Entities.Subject;
import bil0104.vea.Entities.Teacher;
import bil0104.vea.Services.StudentService;
import bil0104.vea.Services.StudyService;
import bil0104.vea.Services.SubjectService;
import bil0104.vea.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class SubjectController extends AbstractController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudyService studyService;

    @GetMapping("/subjects")
    public String list(Model model) {
        model.addAttribute("pageActive", "subjects");
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("metaTitle", messageSource.getMessage("Subjects.Body.Title", null, LocaleContextHolder.getLocale()));
        return "views/subjects/list";
    }

    @GetMapping(value = "/subjects/add")
    @Secured({"ROLE_ADMIN"})
    public String add(Model model) {
        model.addAttribute("pageActive", "subjects");
        model.addAttribute("semesters", Semester.values());
        model.addAttribute("teachers", teacherService.getAll());
        model.addAttribute("metaTitle", messageSource.getMessage("Subjects.Body.Title", null, LocaleContextHolder.getLocale()) + " - " + messageSource.getMessage("Actions.Create", null, LocaleContextHolder.getLocale()));
        return "views/subjects/add";
    }

    @GetMapping(value = "/subjects/{id}/detail")
    public String detail(Model model, @PathVariable long id, @RequestParam(required = false) String ayear) {
        Subject subject = subjectService.findById(id);
        if (ayear == null) {
            ayear = this.getCurrentAcademicYear();
        }
        model.addAttribute("pageActive", "subjects");
        model.addAttribute("subject", subject);
        model.addAttribute("studies", studyService.findBySubjectAndYear(subject, ayear));
        model.addAttribute("metaTitle", messageSource.getMessage("Subjects.Body.Title", null, LocaleContextHolder.getLocale()) + " - " + messageSource.getMessage("Actions.Detail", null, LocaleContextHolder.getLocale()));
        model.addAttribute("semesters", Semester.values());
        model.addAttribute("teachers", teacherService.getAll());
        if (getAuthUser().getRole().isAdmin()) {
            model.addAttribute("students", studentService.getAll());
        }
        return "views/subjects/detail";
    }

    @PostMapping(value = "/subjects/{id}/update")
    @Secured({"ROLE_ADMIN"})
    public String update(@Validated @ModelAttribute("subject") Subject subject, BindingResult subjectResult, @PathVariable long id, @RequestParam(required = false) String ayear) {
        Subject st = subjectService.findById(id);
        st.setAbbreviation(subject.getAbbreviation());
        st.setName(subject.getName());
        st.setSemester(subject.getSemester());
        st.setYear(subject.getYear());
        st.setCredits(subject.getCredits());
        if (subjectResult.getRawFieldValue("teacher.id") != null) {
            st.setTeacher(teacherService.findById((long) subjectResult.getRawFieldValue("teacher.id")));
        } else {
            st.setTeacher(null);
        }
        subjectService.update(st);

        return "redirect:/subjects/" + id + "/detail" + (ayear == null ? "" : "?ayear=" + ayear);
    }

    @PostMapping(value = "/subjects/add")
    @Secured({"ROLE_ADMIN"})
    public String create(@ModelAttribute("subject") @Validated Subject subject, BindingResult subjectResult) {
        if (subjectResult.hasErrors()) {
            System.out.println(subjectResult.getAllErrors());
            return "views/subjects/add";
        }
        if (subjectResult.getRawFieldValue("teacher.id") != null) {
            subject.setTeacher(teacherService.findById((long) subjectResult.getRawFieldValue("teacher.id")));
        }
        subjectService.insert(subject);
        return "redirect:/subjects/add";
    }

    @GetMapping(value = "/subjects/{id}/delete")
    @Secured({"ROLE_ADMIN"})
    public String delete(@PathVariable long id) {
        Subject found = subjectService.findById(id);
        if (found.teacher != null) {
            Teacher t = teacherService.findById(found.teacher.getId());
            List<Subject> newSubs = new ArrayList<>();
            for (Subject s : t.getTeaches()) {
                if (s.getId() != id) {
                    newSubs.add(s);
                }
            }
            t.setTeaches(newSubs);
            teacherService.update(t);
        }
        studyService.deleteWhereSubjectId(id);
        subjectService.delete(found);
        return "redirect:/subjects";
    }
}
