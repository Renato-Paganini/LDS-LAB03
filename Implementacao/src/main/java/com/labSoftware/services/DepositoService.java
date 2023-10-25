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

        return ResponseEntity.ok(deposito);

    }

    public ResponseEntity<?> retornaDepositos() {

        List<Deposito> depositos = depositoRepository.findAll();

        return ResponseEntity.ok(depositos);

    }

    public ResponseEntity<?> retornaTodosDepositosDoProfessor(long id) {
        List<Deposito> transacaos = depositoRepository.findAll();
        List<Deposito> transacaosProfessor = transacaos.stream().filter((p) -> p.getProfessor().getId() == id)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transacaosProfessor);
    }

    public ResponseEntity<?> retornaTodosDepositos(long id) {
        List<Deposito> transacaos = depositoRepository.findAll();
        List<Deposito> transacaosAluno = transacaos.stream().filter((a) -> a.getAluno().getId() == id)
                .collect(Collectors.toList());

        return ResponseEntity.ok(transacaosAluno);

    }

}
