package bil0104.vea.Controllers.Web;

import bil0104.vea.JPA.Study;
import bil0104.vea.JPA.Subject;
import bil0104.vea.Services.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
public class StudyController extends AbstractController {

    @Autowired
    private StudyService studyService;

    @PostMapping(value = "/studies/{id}/update")
    @Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
    public String update(@RequestParam int points, @RequestParam String url, @PathVariable long id) {
        if (points >= 0 && points <= 100) {
            Study study = studyService.find(id);
            if (getAuthUser().getRole().isTeacher() && getAuthUser().getId() != study.getSubject().getTeacher().getId()) {
                return "redirect:/" + url;
            }
            study.setPoints(points);
            studyService.update(study);
        }

        return "redirect:/" + url;
    }

    @GetMapping(value = "/studies/{id}/delete")
    @Secured({"ROLE_ADMIN"})
    public String delete(@PathVariable long id, @RequestParam String url) {
        studyService.delete(id);
        return "redirect:/" + url;
    }
}
