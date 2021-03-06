package bil0104.vea.Controllers.Web;

import bil0104.vea.Entities.Person;
import bil0104.vea.Entities.Role;
import bil0104.vea.Entities.Student;
import bil0104.vea.Entities.Study;
import bil0104.vea.Services.StudentService;
import bil0104.vea.Services.StudyService;
import bil0104.vea.Services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static bil0104.vea.Utils.EncryptedPasswordUtils.encryptPassword;


@Controller
public class StudentController extends AbstractController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudyService studyService;

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

    @PostMapping(value = "/students/{id}/update")
    @Secured({"ROLE_ADMIN"})
    public String update(@RequestParam(required = false) String ayear, @Validated @ModelAttribute("student") Student student, @PathVariable long id) {
        Student st = studentService.findById(id);
        st.setFirstName(student.getFirstName());
        st.setLastName(student.getLastName());
        st.setDateOfBirth(student.getDateOfBirth());
        st.setYear(student.getYear());
        studentService.update(st);
        return ayear == null ? "redirect:/students/" + id + "/detail" : "redirect:/students/" + id + "/detail?ayear=" + ayear;
    }

    @GetMapping(value = "/students/{id}/detail")
    @Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
    public String detail(Model model, @PathVariable long id, @RequestParam(required = false) String ayear) {
        Student student = studentService.findById(id);
        if (ayear == null) {
            ayear = this.getCurrentAcademicYear();
        }
        List<Study> stu = studyService.findByStudentAndYear(student, ayear);
        model.addAttribute("pageActive", "students");
        model.addAttribute("student", student);
        model.addAttribute("studies", stu);
        model.addAttribute("metaTitle", messageSource.getMessage("Students.Body.Title", null, LocaleContextHolder.getLocale()) + " - " + messageSource.getMessage("Actions.Detail", null, LocaleContextHolder.getLocale()));
        if (getAuthUser().getRole().isAdmin()) {
            model.addAttribute("subjects", subjectService.getAll());
        }
        return "views/students/detail";
    }

    @PostMapping(value = "/students/add")
    @Secured("ROLE_ADMIN")
    public String create(@ModelAttribute("student") @Validated Student student, BindingResult studentResult) {
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
        studyService.deleteWhereStudentId(id);
        studentService.delete(id);
        return "redirect:/students";
    }
}
