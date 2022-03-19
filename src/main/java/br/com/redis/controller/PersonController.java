package br.com.redis.controller;

import br.com.redis.model.Person;
import br.com.redis.service.PersonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{uuid}")
    public Person getPersonByUuid(@PathVariable final String uuid) throws Exception {
        return personService.getPersonByUuid(uuid);
    }

    @PostMapping
    public void save(@RequestBody final Person person) {
        personService.save(person);
    }

}
