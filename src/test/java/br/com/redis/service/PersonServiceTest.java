package br.com.redis.service;

import br.com.redis.exception.PersonNotFoundException;
import br.com.redis.model.Person;
import br.com.redis.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static br.com.redis.utils.MockUtils.createPerson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    public void shouldSearchThePersonByIdSuccessfullyTest() {
        final Person person = createPerson("1", "João da Silva", "(44) 4 4444-4444", "joao.silva@gmail.com");

        when(personRepository.findById("1")).thenReturn(Optional.of(person));

        final Person personByUuid = personService.getPersonByUuid("1");

        assertEquals("1", personByUuid.getId());
        assertEquals("João da Silva", personByUuid.getName());
        assertEquals("(44) 4 4444-4444", personByUuid.getPhone());
        assertEquals("joao.silva@gmail.com", personByUuid.getEmail());
    }

    @Test
    public void shouldReturnErrorWhenTryingToSearchForAPersonWithUuidThatDoesNotExistTest() {
        when(personRepository.findById("1")).thenReturn(Optional.empty());

        final PersonNotFoundException thrown = assertThrows(
                PersonNotFoundException.class,
                () -> personService.getPersonByUuid("1")
        );

        assertEquals("id", thrown.getFieldError());
        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
        assertEquals("Person not found", thrown.getMessage());
    }

    @Test
    public void shouldCreateASuccessfulPersonTest() {
        final Person person = createPerson("1", "João da Silva", "(44) 4 4444-4444", "joao.silva@gmail.com");

        personService.save(person);

        verify(personRepository).save(person);
    }

}
