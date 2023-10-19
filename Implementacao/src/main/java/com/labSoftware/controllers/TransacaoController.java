package com.labSoftware.controllers;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labSoftware.services.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/realizaTransacao/aluno")
    public ResponseEntity<?> realizaTransacaoAluno(@RequestBody Map<String, String> jsonMap) {
        System.out.println(jsonMap);
        Long id_vantagem = Long.valueOf(jsonMap.get("id_vantagem").toString());
        Long id_aluno = Long.valueOf(jsonMap.get("id_aluno").toString());
        LocalDate data = LocalDate.parse(jsonMap.get("data").toString());

        return transacaoService.realizaTransacaoAluno(id_aluno, data, id_vantagem);
    }

    @PostMapping("/realizaTransacao/professorToAluno")
    public ResponseEntity<?> realizaTransacaoProfessorToAluno(
            @RequestBody Map<String, String> jsonMap) {
        System.out.println(jsonMap);
        Long id_professor = Long.valueOf(jsonMap.get("id_professor").toString());
        Long id_aluno = Long.valueOf(jsonMap.get("id_aluno").toString());
        LocalDate data = LocalDate.parse(jsonMap.get("data").toString());
        Double valor = Double.valueOf(jsonMap.get("valor").toString());

        return transacaoService.realizaTransacaoProftoAluno(id_professor, id_aluno, data, valor);

    }

    @GetMapping("/retornaTodasTransacoes")
    public ResponseEntity<?> retornaTodasTransacoes() {

        return transacaoService.retornaTodasTransacoes();
    }

    @GetMapping("/retornaTodasTransacoes/aluno/{id}")
    public ResponseEntity<?> retornaTodasTransacoesAluno(@PathVariable Long id) {

        return transacaoService.retornaTodasTransacoesAluno(id);
    }

}
