package com.marcelo.testes.cadastro.domain.controller;

import com.marcelo.testes.cadastro.domain.dto.UserDTO;
import com.marcelo.testes.cadastro.domain.entity.User;
import com.marcelo.testes.cadastro.domain.responses.OkResponse;
import com.marcelo.testes.cadastro.domain.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*")
@Api("Usuários")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(headers = "X-API-VERSION=1")
    @ApiOperation(value = "User List", notes = "Lista Usuários")
    public ResponseEntity<OkResponse<List<User>>> all() {
        OkResponse<List<User>> response = new OkResponse<>();
        try {
            var usuarios = userService.getAll();
                response.setOk(true);
                response.setData(usuarios);
        } catch (Exception e) {
            response.setOk(false);
            response.setErrors(Collections.singletonList(e.getMessage()));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}", headers = "X-API-VERSION=1")
    @ApiOperation(value = "User by ID", notes = "Usuário por ID")
    public ResponseEntity<OkResponse<User>> find(@PathVariable("id") Long id) {
        OkResponse<User> response = new OkResponse<>();
        try {
            var usuario = userService.getOne(id);
            response.setOk(true);
            response.setData(usuario);
        } catch (Exception e) {
            response.setOk(false);
            response.setErrors(Collections.singletonList(e.getMessage()));
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(headers = "X-API-VERSION=1")
    @ApiOperation(value = "User Create", notes = "Cadastra Usuário")
    public ResponseEntity<OkResponse<User>> create(@Valid @RequestBody UserDTO userDTO) {
        OkResponse<User> response = new OkResponse<>();
        try {
            this.userService.validation(userDTO);
            User userStore = this.userService.store(userDTO);
            response.setOk(true);
            response.setData(userStore);
        } catch (Exception e) {
            response.setOk(false);
            response.setErrors(Collections.singletonList(e.getMessage()));
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}",headers = "X-API-VERSION=1")
    @ApiOperation(value = "User Update", notes = "Utualiza Usuário")
    public ResponseEntity<OkResponse<User>> update(@PathVariable("id") Long userId, @Valid @RequestBody UserDTO userDTO) {
        OkResponse<User> response = new OkResponse<>();
        try {
            User userUpdate = this.userService.update(userId, userDTO);
            response.setOk(true);
            response.setData(userUpdate);
        } catch (Exception e) {
            response.setOk(false);
            response.setErrors(Collections.singletonList(e.getMessage()));
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}",headers = "X-API-VERSION=1")
    @ApiOperation(value = "User Delete", notes = "Exclui um Usuário")
    public ResponseEntity<OkResponse<User>> delete(@PathVariable("id") Long userId) {
        OkResponse<User> response = new OkResponse<>();
        try {
            this.userService.destroy(userId);
            response.setOk(true);
            response.setData(null);
        } catch (Exception e) {
            response.setOk(false);
            response.setErrors(Collections.singletonList(e.getMessage()));
        }
        return ResponseEntity.ok(response);
    }

}
