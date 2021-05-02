package com.marcelo.testes.cadastro.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marcelo.testes.cadastro.domain.enums.ProfileGender;
import com.marcelo.testes.cadastro.resource.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(length = 6)
    @Enumerated(EnumType.STRING)
    private ProfileGender gender;

    @Column(length = 100)
    private String email;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;


    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(length = 60)
    private String naturalness;

    @Column(length = 60)
    private String nationality;

}
