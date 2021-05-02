package com.marcelo.testes.cadastro.domain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/source")
public class SourceController {

    @GetMapping
    public void method(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "https://github.com/mdnb-boss/META_teste_Java");
        httpServletResponse.setStatus(302);
    }
}
