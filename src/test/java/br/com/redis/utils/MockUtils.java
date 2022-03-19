package br.com.redis.utils;

import br.com.redis.model.Person;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MockUtils {

    public static Person createPerson(final String id, final String name, final String phone, final String email) {
        return Person.builder()
                .id(id)
                .name(name)
                .phone(phone)
                .email(email)
                .build();
    }

}
