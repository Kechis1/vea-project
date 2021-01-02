package bil0104.vea.Converters;

import bil0104.vea.Entities.Subject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter2 implements Converter<Subject, String> {

    @Override
    public String convert(Subject s) {
        if (s == null) {
            return "null";
        } else {
            return Long.toString(s.getId());
        }
    }
}

