package bil0104.vea.Controllers.Web;

import bil0104.vea.Entities.Person;
import bil0104.vea.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractController {
    @Autowired
    PersonService personService;

    @ModelAttribute("authUser")
    public Person getAuthUser() {
        if (isAuthenticated()) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return personService.findByLogin(user.getUsername());
        }
        return null;
    }

    @ModelAttribute("years")
    public List<String> getAcademicYears() {
        int currentYear = LocalDate.now().getYear();
        List<String> years = new ArrayList<>();
        for (int i = currentYear; i >= 2010; i--) {
            years.add(i + "/" + (i+1));
        }
        return years;
    }

    protected String getCurrentAcademicYear() {
        return this.getAcademicYears().get(0);
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}
