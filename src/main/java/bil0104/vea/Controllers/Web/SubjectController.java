package bil0104.vea.Controllers.Web;

import bil0104.vea.JPA.Semester;
import bil0104.vea.JPA.Subject;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SubjectController extends AbstractController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TeacherService teacherService;

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
    public String detail(Model model, @PathVariable long id) {
        model.addAttribute("pageActive", "subjects");
        model.addAttribute("metaTitle", messageSource.getMessage("Subjects.Body.Title", null, LocaleContextHolder.getLocale()) + " - " + messageSource.getMessage("Actions.Detail", null, LocaleContextHolder.getLocale()));
        model.addAttribute("subject", subjectService.findById(id));
        return "views/subjects/detail";
    }

    @PostMapping(value = "/subjects/add")
    @Secured({"ROLE_ADMIN"})
    public String create(@ModelAttribute("subject") @Validated Subject subject, BindingResult subjectResult) {
        if (subjectResult.hasErrors() || subjectResult.getRawFieldValue("teacher.id") == null) {
            System.out.println(subjectResult.getAllErrors());
            return "views/subjects/add";
        }
        subject.setTeacher(teacherService.findById((long) subjectResult.getRawFieldValue("teacher.id")));
        subjectService.insert(subject);
        return "redirect:/subjects/add";
    }

    @GetMapping(value = "/subjects/{id}/delete")
    @Secured({"ROLE_ADMIN"})
    public String delete(@PathVariable long id) {
        subjectService.delete(id);
        return "redirect:/subjects";
    }
}
