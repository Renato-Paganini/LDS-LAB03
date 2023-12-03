package com.labSoftware.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labSoftware.DTO.ResgateDTO;
import com.labSoftware.services.ResgateService;

@RestController
@RequestMapping("/transacao")
public class ResgateController {

    @Autowired
    private ResgateService resgateService;

    @PostMapping("/resgate")
    public ResponseEntity<?> realizaTransacaoAluno(@RequestBody(required = false) ResgateDTO ResgateDTO) {

        return resgateService.realizaTransacaoAluno(ResgateDTO);
    }

    @GetMapping("/retornaTodosResgates")
    public ResponseEntity<?> retornaTodasTransacoes() {

        return resgateService.retornaResgates();
    }

    @GetMapping("/retornaTodosResgates/{id}")
    public ResponseEntity<?> retornaTodasTransacoesAluno(@PathVariable Long id) {

        return resgateService.retornaTodosResgates(id);
    }
}
