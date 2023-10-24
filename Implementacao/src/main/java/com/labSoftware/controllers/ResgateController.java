package com.labSoftware.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labSoftware.DTO.DepositoDTO;
import com.labSoftware.DTO.ResgateDTO;
import com.labSoftware.models.Deposito;
import com.labSoftware.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/retornaTodosResgate/{id}")
    public ResponseEntity<?> retornaTodasTransacoesAluno(@PathVariable Long id) {

        return resgateService.retornaTodosResgates(id);
    }
}
