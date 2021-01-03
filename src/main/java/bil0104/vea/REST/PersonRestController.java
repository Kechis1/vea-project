package bil0104.vea.REST;

import bil0104.vea.Entities.Person;
import bil0104.vea.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public Person create() {

        return null;
    }

    @RequestMapping(path = "/api/persons/{id}", method = RequestMethod.PUT)
    public void edit(@PathVariable long id) {

    }

    @RequestMapping(path = "/api/persons/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        personService.delete(id);
    }
}
