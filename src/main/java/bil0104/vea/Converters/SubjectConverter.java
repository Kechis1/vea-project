package bil0104.vea.Converters;

import bil0104.vea.JPA.Subject;
import bil0104.vea.Services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter implements Converter<String, Subject> {

    @Autowired
    private SubjectService subjectService;

    @Override
    public Subject convert(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        } else {
            return subjectService.findById(Long.parseLong(s));
        }
    }
}
