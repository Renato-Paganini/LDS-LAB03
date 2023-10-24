package com.labSoftware.services;

import com.labSoftware.DTO.ResgateDTO;
import com.labSoftware.models.Aluno;
import com.labSoftware.models.Deposito;
import com.labSoftware.models.Resgate;
import com.labSoftware.models.Vantagem;
import com.labSoftware.repositories.AlunoRepository;
import com.labSoftware.repositories.ResgateRepository;
import com.labSoftware.repositories.VantagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResgateService {
    @Autowired
    private ResgateRepository resgateRepository;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private VantagemService vantagemService;


        public ResponseEntity<?> realizaTransacaoAluno(ResgateDTO resgate) {
        Aluno aluno = alunoService.findbyIdAluno(resgate.getId_aluno());
        Vantagem vantagem = vantagemService.findbyIdVantagem(resgate.getId_vantagem());

        if (aluno == null || vantagem == null) {
            return new ResponseEntity<>("Vantagem ou Aluno não existem", HttpStatusCode.valueOf(400));
        }

        double creditosVantagem = vantagem.getValor();
        double creditos_aluno = aluno.getSaldo();

        if (creditosVantagem > creditos_aluno) {
            return new ResponseEntity<>("Aluno não possui crédito suficiente", HttpStatusCode.valueOf(400));
        }

        aluno.setSaldo((creditos_aluno - creditosVantagem));
        Resgate resgate1 = new Resgate(resgate.getDescription(),resgate.getData(),aluno,vantagem,vantagem.getValor());

        resgateRepository.save(resgate1);

        alunoRepository.salvar(aluno);

        // mailService.sendMessage(professor.getEmail(), "Você acabou de realizar uma
        // transação no valor de:" +
        // transacao.getValor() + " para o Aluno: " + aluno.getUsuario().getName());

        // mailService.sendMessage(aluno.getUsuario().getEmail(),
        // "Você acabou de receber " + transacao.getValor() + " moedas" +
        // "do professor: " + professor.getNome());

        return ResponseEntity.ok(resgate1);

    }
    public ResponseEntity<?> retornaResgates() {

        List<Resgate> resgates = resgateRepository.findAll();

        return ResponseEntity.ok(resgates);

    }

    public ResponseEntity<?> retornaTodosResgates(long id) {
        List<Resgate> resgates = resgateRepository.findAll();
        List<Resgate> resgatesAluno = resgates.stream().filter((a) -> a.getAluno().getId() == id)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resgatesAluno);

    }
}
