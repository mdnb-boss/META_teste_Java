package com.marcelo.testes.cadastro.resource;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseService<E extends BaseEntity, D extends BaseDTO<E>, R extends BaseRepository<E>> {

    @Autowired
    protected R repository;

    @PersistenceContext(name = "default")
    protected EntityManager em;


    public R getRepository() {
        return repository;
    }

    public EntityManager getEm() {
        return em;
    }

    public Page<E> findAll(Pageable pageable, E filter) throws Exception {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);
        Example<E> example = Example.of(filter, matcher);

        return repository.findAll(example, pageable);
    }

    public List<E> getAll() {
        return repository.findAll();
    }

    public E getOne(Long id) throws BaseException {
        Optional<E> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new BaseException("API.entity_not_found", 404);
    }

    public E store(D dto) throws BaseException {
        E entity = dto.getEntity();
        repository.save(entity);
        return entity;
    }

    public E update(Long id, D dto) throws BaseException {
        E oldEntity = this.getOne(id);
        E entity = dto.getEntity();
        entity.setId(id);
        entity.setCreatedAt(oldEntity.getCreatedAt());
        E newEntity = repository.save(entity);
        return newEntity;
    }

    public void destroy(Long id) {
        E entity = this.getOne(id);
        repository.delete(entity);
    }

}
