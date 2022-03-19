package br.com.redis.service;

import br.com.redis.model.Person;
import br.com.redis.repository.PersonRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Cacheable(value = "personCache")
    public Person getPersonByUuid(final String uuid) throws Exception {
        final Optional<Person> personOptional = personRepository.findById(uuid);

        if (!personOptional.isPresent()) {
            throw new Exception("Person not found");
        }

        return personOptional.get();
    }

    public void save(final Person person) {
        personRepository.save(person);
    }
}
