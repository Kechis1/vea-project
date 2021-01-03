package bil0104.vea.REST;

import bil0104.vea.Entities.Person;
import bil0104.vea.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonRestController {
    @Autowired
    private PersonService personService;

    @RequestMapping(path = "/api/persons", method = RequestMethod.GET)
    public List<Person> getAll() {
        return personService.getAll();
    }

    @RequestMapping(path = "/api/persons/{id}", method = RequestMethod.GET)
    public Person find(@PathVariable long id) {
        return personService.findById(id);
    }

    @RequestMapping(path = "/api/persons", method = RequestMethod.POST)
    public Person create(@Validated @RequestBody Person person) {
        personService.insert(person);
        return null;
    }

    @RequestMapping(path = "/api/persons/{id}", method = RequestMethod.PUT)
    public void edit(@Validated @RequestBody Person person, @PathVariable long id) {
        Person found = personService.findById(id);
        if (found != null) {
            person.setId(id);
            personService.update(person);
        }
    }

    @RequestMapping(path = "/api/persons/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        personService.delete(id);
    }
}
