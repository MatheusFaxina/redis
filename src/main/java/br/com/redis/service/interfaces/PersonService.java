package br.com.redis.service.interfaces;

import br.com.redis.model.Person;

public interface PersonService {

    Person getPersonByUuid(final String uuid);
    void save(final Person person);

}
