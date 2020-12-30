package bil0104.vea.Controllers.Web;

import bil0104.vea.JPA.Person;
import bil0104.vea.JPA.Role;
import bil0104.vea.JPA.Student;
import bil0104.vea.Services.StudentService;
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

import static bil0104.vea.Utils.EncryptedPasswordUtils.encryptPassword;


@Controller
public class StudentController extends AbstractController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String list(Model model) {
        model.addAttribute("pageActive", "students");
        model.addAttribute("students", studentService.getAll());
        model.addAttribute("metaTitle", messageSource.getMessage("Students.Body.Title", null, LocaleContextHolder.getLocale()));
        return "views/students/list";
    }

    @GetMapping(value = "/students/add")
    @Secured({"ROLE_ADMIN"})
    public String add(Model model) {
        model.addAttribute("pageActive", "students");
        model.addAttribute("metaTitle", messageSource.getMessage("Students.Body.Title", null, LocaleContextHolder.getLocale()) + " - " + messageSource.getMessage("Actions.Create", null, LocaleContextHolder.getLocale()));
        return "views/students/add";
    }

    @GetMapping(value = "/students/{id}/detail")
    @Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
    public String detail(Model model) {
        model.addAttribute("pageActive", "students");
        model.addAttribute("metaTitle", messageSource.getMessage("Students.Body.Title", null, LocaleContextHolder.getLocale()) + " - " + messageSource.getMessage("Actions.Detail", null, LocaleContextHolder.getLocale()));
        return "views/students/detail";
    }

    @PostMapping(value = "/students/add")
    @Secured("ROLE_ADMIN")
    public String create(@ModelAttribute @Validated Student student, BindingResult studentResult) {
        if (studentResult.hasErrors()) {
            System.out.println(studentResult.getAllErrors());
            return "views/students/add";
        }
        student.setLogin(Person.findNextLogin(student, studentService::findByLogin));
        student.setRole(Role.STUDENT);
        student.setPassword(encryptPassword(student.getPassword()));
        studentService.insert(student);
        return "redirect:/students/add";
    }

    @GetMapping(value = "/students/{id}/delete")
    @Secured("ROLE_ADMIN")
    public String delete(@PathVariable long id) {
        studentService.delete(id);
        return "redirect:/students";
    }
}
