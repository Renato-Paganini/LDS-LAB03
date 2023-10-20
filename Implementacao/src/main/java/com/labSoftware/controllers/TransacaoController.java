package com.labSoftware.controllers;

import java.time.LocalDate;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labSoftware.DTO.TransacaoDTO;
import com.labSoftware.models.Transacao;
import com.labSoftware.models.Vantagem;
import com.labSoftware.repositories.AlunoRepository;
import com.labSoftware.services.AlunoService;
import com.labSoftware.services.ProfessorService;
import com.labSoftware.services.VantagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
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
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProfessorService professorService;


    @Autowired
    private VantagemService vantagemService;
    private Transacao mapDTOToEntityAluno(TransacaoDTO dto) {
        Transacao transacao = new Transacao();
        Vantagem vantagem = vantagemService.findbyIdVantagem(dto.getId_vantagem());
        transacao.setVantagem(vantagem);
        transacao.setAluno(alunoService.findbyIdAluno(dto.getId_aluno()));
        transacao.setData(dto.getData());
        transacao.setValor(-vantagem.getValor());
        return transacao;
    }

    private Transacao mapDTOToEntityProfToAluno(TransacaoDTO dto) {
        Transacao transacao = new Transacao();
        transacao.setProfessor(professorService.findByIdProfessor(dto.getId_professor()));
        transacao.setAluno(alunoService.findbyIdAluno(dto.getId_aluno()));
        transacao.setData(dto.getData());
        transacao.setValor(dto.getValor());
        transacao.setJustificativa(dto.getJustificativa());
        return transacao;
    }


    @PostMapping("/realizaTransacao/aluno")
    public ResponseEntity<?> realizaTransacaoAluno(@RequestBody (required = false) TransacaoDTO transacaoDTO) {
        Transacao transacao = mapDTOToEntityAluno(transacaoDTO);

        return transacaoService.realizaTransacaoAluno(transacao);
    }

    @PostMapping("/realizaTransacao/professorToAluno")
    public ResponseEntity<?> realizaTransacaoProfessorToAluno(
            @RequestBody (required = false) TransacaoDTO transacaoDTO) {
        System.out.println(transacaoDTO);
        Transacao transacao = mapDTOToEntityProfToAluno(transacaoDTO);

        return transacaoService.realizaTransacaoProftoAluno(transacao);

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
