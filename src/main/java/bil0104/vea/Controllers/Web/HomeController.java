package bil0104.vea.Controllers.Web;

import bil0104.vea.Entities.Person;
import bil0104.vea.Services.StudyService;
import bil0104.vea.Services.StudentService;
import bil0104.vea.Services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;


@Controller
public class HomeController extends AbstractController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    StudentService studentService;

    @Autowired
    StudyService studyService;

    @Autowired
    SubjectService subjectService;

    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String home(Model model, Principal principal, @RequestParam(required = false) String year) {
        Person person = personService.findByLogin(principal.getName());
        model.addAttribute("pageActive", "home");
        model.addAttribute("metaTitle", messageSource.getMessage("Nav.Home", null, LocaleContextHolder.getLocale()));

        switch (person.getRole()) {
            case ADMIN:
                return "views/home/admin";
            case STUDENT:
                if (year == null) {
                    year = this.getCurrentAcademicYear();
                }
                model.addAttribute("studies", studyService.findByStudentAndYear(person, year));
                return "views/home/student";
            case TEACHER:
                return "views/home/teacher";
            default:
                return "views/errors/403";
        }
    }
}
