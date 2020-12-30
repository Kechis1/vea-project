package bil0104.vea.Controllers.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController extends AbstractController {
    @Autowired
    MessageSource messageSource;

    @GetMapping("/login")
    public String login(Model model) {
        if (isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("pageActive", "login");
        model.addAttribute("metaTitle", messageSource.getMessage("Home.Login.Body.Title", null, LocaleContextHolder.getLocale()));
        return "views/home/login";
    }

    @RequestMapping("/error/403")
    public String error(Model model) {
        model.addAttribute("metaTitle", messageSource.getMessage("Errors.NotAllowed", null, LocaleContextHolder.getLocale()));
        return "views/errors/403";
    }
}
