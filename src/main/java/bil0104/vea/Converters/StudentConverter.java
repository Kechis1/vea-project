package bil0104.vea.Converters;

import bil0104.vea.Entities.Student;
import bil0104.vea.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter implements Converter<String, Student> {

    @Autowired
    private StudentService studentService;

    @Override
    public Student convert(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        } else {
            return studentService.findById(Long.parseLong(s));
        }
    }
}
