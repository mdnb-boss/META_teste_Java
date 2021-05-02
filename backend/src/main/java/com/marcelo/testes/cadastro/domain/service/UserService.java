package com.marcelo.testes.cadastro.domain.service;

import com.marcelo.testes.cadastro.domain.dto.UserDTO;
import com.marcelo.testes.cadastro.domain.entity.User;
import com.marcelo.testes.cadastro.domain.repository.UserRepository;
import com.marcelo.testes.cadastro.domain.util.DataValidade;
import com.marcelo.testes.cadastro.domain.util.ValidaCPF;
import com.marcelo.testes.cadastro.resource.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService extends BaseService<User, UserDTO, UserRepository> {

    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean existsUserFromCPF(String cpf) {
        return userRepository.findByCpf(cpf) != null;
    }

    private boolean isValidCpf(String cpf) {
        return ValidaCPF.isCPF(cpf);
    }

    public void validation(UserDTO userDTO) throws Exception {
        if (!isValidCpf(userDTO.getCpf())) {
            throw new Exception("CPF inválido.");
        } else {
            if (existsUserFromCPF(userDTO.getCpf())) {
                throw new Exception("CPF já está cadastrado.");
            }
        }
        if (!DataValidade.valid(userDTO.getBirthDate())) {
            throw new Exception("Data de Nascimento é inválido.");
        }
    }
}

