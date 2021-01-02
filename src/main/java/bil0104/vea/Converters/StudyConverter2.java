package bil0104.vea.Converters;

import bil0104.vea.Entities.Study;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudyConverter2 implements Converter<Study, String> {

    @Override
    public String convert(Study s) {
        if (s == null) {
            return "null";
        } else {
            return Long.toString(s.getId());
        }
    }
}
