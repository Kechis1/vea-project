package bil0104.vea.Controllers.Web;

import bil0104.vea.JPA.Person;
import bil0104.vea.JPA.Teacher;
import bil0104.vea.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.Normalizer;

@Controller
public class TeacherController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teachers")
    public String list(Model model) {
        model.addAttribute("pageActive", "teachers");
        model.addAttribute("teachers", teacherService.getAll());
        model.addAttribute("metaTitle", messageSource.getMessage("Teachers.Body.Title", null, LocaleContextHolder.getLocale()));
        return "views/teachers/list";
    }

    @GetMapping(value = "/teachers/add")
    public String add(Model model) {
        model.addAttribute("pageActive", "teachers");
        model.addAttribute("metaTitle", messageSource.getMessage("Teachers.Body.Title", null, LocaleContextHolder.getLocale()) + " - " + messageSource.getMessage("Actions.Create", null, LocaleContextHolder.getLocale()));
        return "views/teachers/add";
    }

    @PostMapping(value = "/teachers/add")
    public String create(@ModelAttribute @Validated Teacher teacher, BindingResult teacherResult) {
        if (teacherResult.hasErrors()) {
            System.out.println(teacherResult.getAllErrors());
            return "views/teachers/add";
        }
        teacher.setLogin(Person.findNextLogin(teacher, teacherService::findByLogin));
        teacherService.insert(teacher);
        return "redirect:/teachers/add";
    }

    @GetMapping(value = "/teachers/{id}/delete")
    public String delete(@PathVariable long id) {
        teacherService.delete(id);
        return "redirect:/teachers";
    }
}
