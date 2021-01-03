package bil0104.vea.Converters;

import bil0104.vea.Entities.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter2 implements Converter<Person, String> {

    @Override
    public String convert(Person s) {
        if (s == null) {
            return "null";
        } else {
            return Long.toString(s.getId());
        }
    }
}
