package com.labSoftware.services;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.labSoftware.dtos.TransacaoDTO;
import com.labSoftware.models.Aluno;
import com.labSoftware.models.Professor;
import com.labSoftware.models.Transacao;
import com.labSoftware.repositories.IAlunoJpaRepository;
import com.labSoftware.repositories.ProfessorRepository;
import com.labSoftware.repositories.TransacaoRepository;

@Service
public class TransacaoService {

    private final IAlunoJpaRepository alunoRepository;

    private final ProfessorRepository professorRepository;

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(IAlunoJpaRepository alunoRepository,
            ProfessorRepository professorRepository,
            TransacaoRepository transacaoRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public ResponseEntity<?> realizaTransacao(TransacaoDTO transacaoDTO) {

        Aluno aluno = alunoRepository.getReferenceById(transacaoDTO.id_aluno());
        Professor professor = professorRepository.getReferenceById(transacaoDTO.id_professor());

        if (aluno == null || professor == null) {
            return new ResponseEntity<>("Professor ou Aluno não existem", HttpStatusCode.valueOf(400));
        }

        double creditos = professor.getSaldo();
        double creditos_transferidos = transacaoDTO.valor();
        double creditos_aluno = aluno.getSaldo();

        if (creditos < creditos_transferidos) {
            return new ResponseEntity<>("Professor não possui crédito suficiente", HttpStatusCode.valueOf(400));
        }

        professor.setSaldo((creditos - creditos_transferidos));
        aluno.setSaldo(creditos_aluno + creditos_transferidos);

        Transacao transacao = new Transacao(professor, aluno, creditos_transferidos, transacaoDTO.data());

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

    public ResponseEntity<?> retornaTodasTransacoes() {

        List<Transacao> transacaos = transacaoRepository.findAll();

        return ResponseEntity.ok(transacaos);

    }

}
