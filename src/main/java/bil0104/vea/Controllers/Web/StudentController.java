package bil0104.vea.Controllers.Web;

import bil0104.vea.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String list(Model model) {
        model.addAttribute("students", studentService.getAll());
        return "views/students/list";
    }

}
