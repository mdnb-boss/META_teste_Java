package com.marcelo.testes.cadastro.domain.service;

import com.marcelo.testes.cadastro.domain.entity.User;
import com.marcelo.testes.cadastro.domain.security.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class BasicUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        }else {
            return UserFactory.create(user);
        }
    }
}