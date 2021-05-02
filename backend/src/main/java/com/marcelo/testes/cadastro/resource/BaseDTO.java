package com.marcelo.testes.cadastro.resource;

public interface BaseDTO<E extends BaseEntity> {
    E getEntity();
}
