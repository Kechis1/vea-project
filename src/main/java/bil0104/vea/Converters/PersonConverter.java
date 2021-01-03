package bil0104.vea.Converters;

import bil0104.vea.Entities.Person;
import bil0104.vea.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter implements Converter<String, Person> {

    @Autowired
    private PersonService personService;

    @Override
    public Person convert(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        } else {
            return personService.findById(Long.parseLong(s));
        }
    }
}
