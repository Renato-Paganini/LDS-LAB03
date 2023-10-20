package com.labSoftware.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.labSoftware.models.Aluno;
import com.labSoftware.models.Professor;
import com.labSoftware.models.Transacao;
import com.labSoftware.models.Vantagem;
import com.labSoftware.repositories.IAlunoJpaRepository;
import com.labSoftware.repositories.ProfessorRepository;
import com.labSoftware.repositories.TransacaoRepository;
import com.labSoftware.repositories.VantagemRepository;

@Service
@JsonIgnoreType
public class TransacaoService {

    @Autowired
    private IAlunoJpaRepository alunoRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private VantagemRepository vantagemRepository;

    public ResponseEntity<?> realizaTransacaoProftoAluno(Transacao transacao) {

        Aluno aluno = transacao.getAluno();
        Professor professor = transacao.getProfessor();

        if (aluno == null || professor == null ) {
            return new ResponseEntity<>("Professor ou Aluno não existem", HttpStatusCode.valueOf(400));
        } else if( transacao.getJustificativa() ==null){
            return new ResponseEntity<>("Faltou uma justificativa", HttpStatusCode.valueOf(400));
        }

        double creditos = professor.getSaldo();
        double creditos_transferidos = transacao.getValor();
        double creditos_aluno = aluno.getSaldo();

        if (creditos < creditos_transferidos) {
            return new ResponseEntity<>("Professor não possui crédito suficiente", HttpStatusCode.valueOf(400));
        }

        professor.setSaldo((creditos - creditos_transferidos));
        aluno.setSaldo(creditos_aluno + creditos_transferidos);

        transacaoRepository.save(transacao);
        professorRepository.saveAndFlush(professor);
        alunoRepository.saveAndFlush(aluno);

        // mailService.sendMessage(professor.getEmail(), "Você acabou de realizar uma
        // transação no valor de:" +
        // transacao.getValor() + " para o Aluno: " + aluno.getUsuario().getName());

        // mailService.sendMessage(aluno.getUsuario().getEmail(),
        // "Você acabou de receber " + transacao.getValor() + " moedas" +
        // "do professor: " + professor.getNome());

        return ResponseEntity.ok(transacao);

    }

    public ResponseEntity<?> realizaTransacaoAluno(Transacao transacao) {
//
//        Aluno aluno = alunoRepository.getReferenceById(id_aluno);
//        Vantagem vantagem = vantagemRepository.getReferenceById(id_vantagem);
        Aluno aluno = transacao.getAluno();
        Vantagem vantagem = transacao.getVantagem();

        if (aluno == null || vantagem == null) {
            return new ResponseEntity<>("Vantagem ou Aluno não existem", HttpStatusCode.valueOf(400));
        }

        double creditosVantagem = vantagem.getValor();
        double creditos_aluno = aluno.getSaldo();

        if (creditosVantagem > creditos_aluno) {
            return new ResponseEntity<>("Aluno não possui crédito suficiente", HttpStatusCode.valueOf(400));
        }

        aluno.setSaldo((creditos_aluno - creditosVantagem));



        transacaoRepository.save(transacao);
        alunoRepository.saveAndFlush(aluno);

        // mailService.sendMessage(professor.getEmail(), "Você acabou de realizar uma
        // transação no valor de:" +
        // transacao.getValor() + " para o Aluno: " + aluno.getUsuario().getName());

        // mailService.sendMessage(aluno.getUsuario().getEmail(),
        // "Você acabou de receber " + transacao.getValor() + " moedas" +
        // "do professor: " + professor.getNome());

        return ResponseEntity.ok(transacao);

    }

    public ResponseEntity<?> retornaTodasTransacoes() {

        List<Transacao> transacaos = transacaoRepository.findAll();

        return ResponseEntity.ok(transacaos);

    }

    public ResponseEntity<?> retornaTodasTransacoesAluno(long id) {
        List<Transacao> transacaos = transacaoRepository.findAll();
        List<Transacao> transacaosAluno = transacaos.stream().filter((a) -> a.getAluno().getId() == id)
                .collect(Collectors.toList());

        return ResponseEntity.ok(transacaosAluno);

    }

}
