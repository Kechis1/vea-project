package bil0104.vea.Controllers.Web;

import bil0104.vea.JPA.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @Autowired
    MessageSource messageSource;

    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String home(Model model) {
        Person user = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("pageActive", "home");
        model.addAttribute("metaTitle", messageSource.getMessage("Nav.Home", null, LocaleContextHolder.getLocale()));

        switch (user.getRole()) {
            case ADMIN:
                return "views/home/admin";
            case STUDENT:
                return "views/home/student";
            case TEACHER:
                return "views/home/teacher";
            default:
                return "views/errors/403";
        }
    }
}
