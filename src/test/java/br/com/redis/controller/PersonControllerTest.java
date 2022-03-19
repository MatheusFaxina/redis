package br.com.redis.controller;

import br.com.redis.AbstractIntegrationTest;
import br.com.redis.model.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.redis.utils.MockUtils.createPerson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldSearchThePersonByIdSuccessfullyTest() throws Exception {
        mockMvc.perform(get("/persons/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("João da Silva"))
                .andExpect(jsonPath("$.phone").value("(44) 4 4444-4444"))
                .andExpect(jsonPath("$.email").value("joao.silva@gmail.com"))
                .andReturn();
    }

    @Test
    public void shouldReturnErrorWhenTryingToSearchForAPersonWithUuidThatDoesNotExistTest() throws Exception {
        mockMvc.perform(get("/persons/PERSON-NOT-FOUND")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.codeStatus").value(404))
                .andExpect(jsonPath("$.field").value("id"))
                .andExpect(jsonPath("$.description").value("Person not found"))
                .andReturn();
    }

    @Test
    public void shouldCreateASuccessfulPersonTest() throws Exception {
        final Person person = createPerson("2", "Name", "(44) 4 4444-4444", "name@gmail.com");

        mockMvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andReturn();
    }

}
