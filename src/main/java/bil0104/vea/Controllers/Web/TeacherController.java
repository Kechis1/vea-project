package bil0104.vea.Controllers.Web;

import bil0104.vea.JPA.*;
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

import java.text.Normalizer;
import java.util.List;

import static bil0104.vea.Utils.EncryptedPasswordUtils.encryptPassword;

@Controller
public class TeacherController extends AbstractController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/teachers")
    public String list(Model model) {
        model.addAttribute("pageActive", "teachers");
        model.addAttribute("teachers", teacherService.getAll());
        model.addAttribute("metaTitle", messageSource.getMessage("Teachers.Body.Title", null, LocaleContextHolder.getLocale()));
        return "views/teachers/list";
    }

    @GetMapping(value = "/teachers/add")
    @Secured({"ROLE_ADMIN"})
    public String add(Model model) {
        model.addAttribute("pageActive", "teachers");
        model.addAttribute("metaTitle", messageSource.getMessage("Teachers.Body.Title", null, LocaleContextHolder.getLocale()) + " - " + messageSource.getMessage("Actions.Create", null, LocaleContextHolder.getLocale()));
        return "views/teachers/add";
    }

    @PostMapping(value = "/teachers/add")
    @Secured({"ROLE_ADMIN"})
    public String create(@ModelAttribute @Validated Teacher teacher, BindingResult teacherResult) {
        if (teacherResult.hasErrors()) {
            System.out.println(teacherResult.getAllErrors());
            return "views/teachers/add";
        }
        teacher.setLogin(Person.findNextLogin(teacher, teacherService::findByLogin));
        teacher.setRole(Role.TEACHER);
        teacher.setPassword(encryptPassword(teacher.getPassword()));
        teacherService.insert(teacher);
        return "redirect:/teachers/add";
    }

    @GetMapping(value = "/teachers/{id}/detail")
    public String detail(Model model, @PathVariable long id) {
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("pageActive", "teachers");
        model.addAttribute("teacher", teacher);
        model.addAttribute("metaTitle", messageSource.getMessage("Teachers.Body.Title", null, LocaleContextHolder.getLocale()) + " - " + messageSource.getMessage("Actions.Detail", null, LocaleContextHolder.getLocale()));
        if (getAuthUser().getRole().isAdmin()) {
            model.addAttribute("subjects", subjectService.getWithoutTeacher());
        }
        return "views/teachers/detail";
    }

    @PostMapping(value = "/teachers/{id}/update")
    @Secured({"ROLE_ADMIN"})
    public String update(@Validated @ModelAttribute Teacher teacher, @PathVariable long id) {
        Teacher st = teacherService.findById(id);
        st.setFirstName(teacher.getFirstName());
        st.setLastName(teacher.getLastName());
        st.setDateOfBirth(teacher.getDateOfBirth());
        teacherService.update(st);
        return "redirect:/teachers/" + id + "/detail";
    }

    @PostMapping(value = "/teachers/{id}/subject/attach")
    @Secured({"ROLE_ADMIN"})
    public String attachSubject(@RequestParam int subjectId, @PathVariable long id) {
        Teacher st = teacherService.findById(id);
        st.addTeaches(subjectService.findById(subjectId));
        teacherService.update(st);
        return "redirect:/teachers/" + id + "/detail";
    }

    @GetMapping(value = "/teachers/{id}/delete")
    @Secured({"ROLE_ADMIN"})
    public String delete(@PathVariable long id) {
        subjectService.detachTeacher(id);
        teacherService.delete(id);
        return "redirect:/teachers";
    }

    @GetMapping(value = "/teachers/{id}/subject/{subjectId}/detach")
    @Secured({"ROLE_ADMIN"})
    public String detachSubject(@PathVariable long id, @PathVariable long subjectId) {
        Teacher teacher = teacherService.findById(id);
        Subject teaches = teacher.getTeaches().stream().filter(x -> x.getId() == subjectId).findFirst().orElse(null);
        if (teaches != null) {
            teacher.getTeaches().remove(teaches);
            teaches.setTeacher(null);
            teacherService.update(teacher);
            subjectService.update(teaches);
        }
        return "redirect:/teachers/" + id + "/detail";
    }
}
