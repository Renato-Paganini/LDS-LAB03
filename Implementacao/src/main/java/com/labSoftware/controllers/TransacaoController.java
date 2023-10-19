package com.labSoftware.controllers;

import com.labSoftware.dtos.TransacaoDTO;
import com.labSoftware.services.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("api/v1/transacao/")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("realizaTransacao/aluno")
    public ResponseEntity<?> realizaTransacaoAlno(
            @RequestBody Map<String,String> jsonMap
    ){
        System.out.println(jsonMap);
        Long id_vantagem =Long.valueOf(jsonMap.get("id_vantagem").toString());
        Long id_aluno =Long.valueOf(jsonMap.get("id_aluno").toString());
        LocalDate data = LocalDate.parse(jsonMap.get("data").toString());

        return transacaoService.realizaTransacaoAluno(id_aluno,data,id_vantagem);

    }
    @PostMapping("realizaTransacao/professorToAluno")
    public ResponseEntity<?> realizaTransacaoProfessorToAluno(
            @RequestBody Map<String,String> jsonMap
    ){
        System.out.println(jsonMap);
        Long id_professor =Long.valueOf(jsonMap.get("id_professor").toString());
        Long id_aluno =Long.valueOf(jsonMap.get("id_aluno").toString());
        LocalDate data = LocalDate.parse(jsonMap.get("data").toString());
        Double valor =Double.valueOf(jsonMap.get("valor").toString());

        return transacaoService.realizaTransacaoProftoAluno(id_professor,id_aluno,data,valor);

    }

    @GetMapping("retornaTodasTransacoes")
    public ResponseEntity<?> retornaTodasTransacoes(){

        return transacaoService.retornaTodasTransacoes();
    }

    @GetMapping("retornaTodasTransacoes/aluno/{id}")
    public ResponseEntity<?> retornaTodasTransacoesAluno(@PathVariable Long id){

        return transacaoService.retornaTodasTransacoesAluno(id);
    }


}
