package com.marcelo.testes.cadastro.resource;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository <T extends BaseEntity> extends JpaRepository <T, Serializable> {

}
