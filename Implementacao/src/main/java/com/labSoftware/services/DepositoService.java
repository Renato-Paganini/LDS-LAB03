package com.labSoftware.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.labSoftware.models.Aluno;
import com.labSoftware.models.Deposito;
import com.labSoftware.models.Professor;
import com.labSoftware.repositories.DepositoRepository;
import com.labSoftware.repositories.IAlunoJpaRepository;
import com.labSoftware.repositories.ProfessorRepository;

@Service
@JsonIgnoreType
public class DepositoService {

    @Autowired
    private IAlunoJpaRepository alunoRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private DepositoRepository depositoRepository;

    public ResponseEntity<?> realizaDeposito(Deposito deposito) {

        Aluno aluno = deposito.getAluno();
        Professor professor = deposito.getProfessor();

        if (aluno == null || professor == null) {
            return new ResponseEntity<>("Professor ou Aluno não existem", HttpStatusCode.valueOf(400));
        } else if (deposito.getDescription() == null) {
            return new ResponseEntity<>("Faltou uma justificativa", HttpStatusCode.valueOf(400));
        }

        double creditos = professor.getSaldo();
        double creditos_transferidos = deposito.getValor();
        double creditos_aluno = aluno.getSaldo();

        if (creditos < creditos_transferidos) {
            return new ResponseEntity<>("Professor não possui crédito suficiente", HttpStatusCode.valueOf(400));
        }

        professor.setSaldo((creditos - creditos_transferidos));
        aluno.setSaldo(creditos_aluno + creditos_transferidos);

        depositoRepository.save(deposito);
        professorRepository.saveAndFlush(professor);
        alunoRepository.saveAndFlush(aluno);

        // mailService.sendMessage(professor.getEmail(), "Você acabou de realizar uma
        // transação no valor de:" +
        // transacao.getValor() + " para o Aluno: " + aluno.getUsuario().getName());

        // mailService.sendMessage(aluno.getUsuario().getEmail(),
        // "Você acabou de receber " + transacao.getValor() + " moedas" +
        // "do professor: " + professor.getNome());

        return ResponseEntity.ok(deposito);

    }

    // public ResponseEntity<?> realizaTransacaoAluno(Deposito transacao) {
    ////
    //// Aluno aluno = alunoRepository.getReferenceById(id_aluno);
    //// Vantagem vantagem = vantagemRepository.getReferenceById(id_vantagem);
    // Aluno aluno = transacao.getAluno();
    // Vantagem vantagem = transacao.getVantagem();
    //
    // if (aluno == null || vantagem == null) {
    // return new ResponseEntity<>("Vantagem ou Aluno não existem",
    // HttpStatusCode.valueOf(400));
    // }
    //
    // double creditosVantagem = vantagem.getValor();
    // double creditos_aluno = aluno.getSaldo();
    //
    // if (creditosVantagem > creditos_aluno) {
    // return new ResponseEntity<>("Aluno não possui crédito suficiente",
    // HttpStatusCode.valueOf(400));
    // }
    //
    // aluno.setSaldo((creditos_aluno - creditosVantagem));
    //
    //
    //
    // depositoRepository.save(transacao);
    // alunoRepository.saveAndFlush(aluno);
    //
    // // mailService.sendMessage(professor.getEmail(), "Você acabou de realizar uma
    // // transação no valor de:" +
    // // transacao.getValor() + " para o Aluno: " + aluno.getUsuario().getName());
    //
    // // mailService.sendMessage(aluno.getUsuario().getEmail(),
    // // "Você acabou de receber " + transacao.getValor() + " moedas" +
    // // "do professor: " + professor.getNome());
    //
    // return ResponseEntity.ok(transacao);
    //
    // }

    public ResponseEntity<?> retornaDepositos() {

        List<Deposito> depositos = depositoRepository.findAll();

        return ResponseEntity.ok(depositos);

    }

    public ResponseEntity<?> retornaTodosDepositos(long id) {
        List<Deposito> transacaos = depositoRepository.findAll();
        List<Deposito> transacaosAluno = transacaos.stream().filter((a) -> a.getAluno().getId() == id)
                .collect(Collectors.toList());

        return ResponseEntity.ok(transacaosAluno);

    }

}
