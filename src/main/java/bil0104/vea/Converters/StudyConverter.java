package bil0104.vea.Converters;

import bil0104.vea.JPA.Study;
import bil0104.vea.Services.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudyConverter implements Converter<String, Study> {

    @Autowired
    private StudyService studyService;

    @Override
    public Study convert(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        } else {
            return studyService.find(Long.parseLong(s));
        }
    }
}
