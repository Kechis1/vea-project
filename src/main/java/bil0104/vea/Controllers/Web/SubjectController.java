package bil0104.vea.Controllers.Web;

import bil0104.vea.Services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubjectController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subjects")
    public String list(Model model) {
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("metaTitle", messageSource.getMessage("Subjects.Head.Title", null, LocaleContextHolder.getLocale()));
        return "views/subjects/list";
    }

}
