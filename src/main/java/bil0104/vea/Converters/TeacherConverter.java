package bil0104.vea.Converters;

import bil0104.vea.Entities.Teacher;
import bil0104.vea.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TeacherConverter implements Converter<String, Teacher> {

    @Autowired
    private TeacherService teacherService;

    @Override
    public Teacher convert(String s) {
        if (s == null || s.equals("0") || s.isEmpty()) {
            return null;
        } else {
            return teacherService.findById(Long.parseLong(s));
        }
    }
}
