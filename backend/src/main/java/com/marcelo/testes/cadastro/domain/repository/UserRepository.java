package com.marcelo.testes.cadastro.domain.repository;

import com.marcelo.testes.cadastro.domain.entity.User;
import com.marcelo.testes.cadastro.resource.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {

    User findById(int id);

    User findByEmail(String findByEmail);

    User findByCpf(String cpf);

}
