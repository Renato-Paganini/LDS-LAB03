package com.labSoftware.controllers;

import com.labSoftware.dtos.TransacaoDTO;
import com.labSoftware.services.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transacao/")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("realizaTransacao")
    public ResponseEntity<?> realizaTransacao(
            @RequestBody TransacaoDTO transacaoDTO
    ){

        return transacaoService.realizaTransacao(transacaoDTO);

    }

    @GetMapping("retornaTodasTransacoes")
    public ResponseEntity<?> retornaTodasTransacoes(){

        return transacaoService.retornaTodasTransacoes();
    }


}
