package com.marcelo.testes.cadastro.domain.security;

import com.marcelo.testes.cadastro.domain.entity.User;

import java.util.ArrayList;

public class UserFactory {

    public static BasicUser create(User user) {
        return new BasicUser(
                user.getId(),
                user.getEmail(),
                "",
                new ArrayList<>());
    }

}
