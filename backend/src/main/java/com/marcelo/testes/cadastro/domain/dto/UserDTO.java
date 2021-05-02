package com.marcelo.testes.cadastro.domain.dto;

import com.marcelo.testes.cadastro.domain.entity.User;
import com.marcelo.testes.cadastro.domain.enums.ProfileGender;
import com.marcelo.testes.cadastro.resource.BaseDTO;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserDTO implements BaseDTO<User> {

    @NotEmpty(message = "Nome é obrigatório")
    private String name;

    private ProfileGender gender;

    @Email(message = "Email deve ser válido")
    private String email;

    @NotNull(message = "Data de Nascimento é obrigatório")
    private Date birthDate;

    @NotEmpty(message = "CPF é obrigatório")
    private String cpf;

    private String naturalness;

    private String nationality;

    @Override
    public User getEntity() {

        var user = new User();
        user.setName(name);
        user.setGender(gender);
        user.setEmail(email);
        user.setBirthDate(birthDate);
        user.setCpf(cpf);
        user.setNaturalness(naturalness);
        user.setNationality(nationality);

        return user;
    }
}
