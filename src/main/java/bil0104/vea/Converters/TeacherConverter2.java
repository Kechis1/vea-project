package bil0104.vea.Converters;

import bil0104.vea.Entities.Teacher;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TeacherConverter2 implements Converter<Teacher, String> {

    @Override
    public String convert(Teacher s) {
        if (s == null) {
            return "null";
        } else {
            return Long.toString(s.getId());
        }
    }
}

