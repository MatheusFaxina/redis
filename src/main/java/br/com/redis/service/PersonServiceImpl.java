package br.com.redis.service;

import br.com.redis.exception.PersonNotFoundException;
import br.com.redis.model.Person;
import br.com.redis.repository.PersonRepository;
import br.com.redis.service.interfaces.PersonService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Cacheable(value = "personCache")
    public Person getPersonByUuid(final String uuid) {
        final Optional<Person> personOptional = personRepository.findById(uuid);

        if (!personOptional.isPresent()) {
            throw new PersonNotFoundException("Person not found", "id", HttpStatus.NOT_FOUND);
        }

        return personOptional.get();
    }

    @Override
    public void save(final Person person) {
        personRepository.save(person);
    }

}
