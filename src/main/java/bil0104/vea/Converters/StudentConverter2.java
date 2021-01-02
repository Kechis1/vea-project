package bil0104.vea.Converters;

import bil0104.vea.Entities.Student;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter2 implements Converter<Student, String> {

    @Override
    public String convert(Student s) {
        if (s == null) {
            return "null";
        } else {
            return Long.toString(s.getId());
        }
    }
}
