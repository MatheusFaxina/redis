package br.com.redis.controller;

import br.com.redis.model.Person;
import br.com.redis.service.interfaces.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{uuid}")
    public Person getPersonByUuid(@PathVariable final String uuid) {
        return personService.getPersonByUuid(uuid);
    }

    @PostMapping
    public void save(@RequestBody final Person person) {
        personService.save(person);
    }

}
