package com.labSoftware.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labSoftware.DTO.DepositoDTO;
import com.labSoftware.models.Deposito;
import com.labSoftware.services.AlunoService;
import com.labSoftware.services.ProfessorService;
import com.labSoftware.services.VantagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labSoftware.services.DepositoService;

@RestController
@RequestMapping("/transacao")
public class DepositoController {

    @Autowired
    private DepositoService depositoService;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private VantagemService vantagemService;

    public ResponseEntity<?> realizarDeposito(
            @RequestBody (required = false) DepositoDTO transacaoDTO) {

        Deposito transacao = new Deposito();
        transacao.setProfessor(professorService.findByIdProfessor(transacaoDTO.getId_professor()));
        transacao.setAluno(alunoService.findbyIdAluno(transacaoDTO.getId_aluno()));
        transacao.setData(transacaoDTO.getData());
        transacao.setValor(transacaoDTO.getValor());
        transacao.setDescription(transacaoDTO.getDescription());

        return depositoService.realizaDeposito(transacao);

    }

    @GetMapping("/retornaTodosDepositos")
    public ResponseEntity<?> retornaTodasTransacoes() {

        return depositoService.retornaDepositos();
    }

    @GetMapping("/retornaTodosDepositos/{id}")
    public ResponseEntity<?> retornaTodasTransacoesAluno(@PathVariable Long id) {

        return depositoService.retornaTodosDepositos(id);
    }

}
