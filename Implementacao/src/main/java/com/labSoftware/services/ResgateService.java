package com.labSoftware.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.labSoftware.DTO.ResgateDTO;
import com.labSoftware.models.Aluno;
import com.labSoftware.models.Empresa;
import com.labSoftware.models.Resgate;
import com.labSoftware.models.Vantagem;
import com.labSoftware.repositories.AlunoRepository;
import com.labSoftware.repositories.EmpresaRepository;
import com.labSoftware.repositories.ResgateRepository;

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
    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private EmailSenderStructureService emailSenderStructureService;
    @Autowired
    private EmpresaRepository empresaRepository;

    public ResponseEntity<?> realizaTransacaoAluno(ResgateDTO resgateDTO) {
        Vantagem vantagem = vantagemService.findbyIdVantagem(resgateDTO.getId_vantagem());
        Aluno aluno = alunoService.findbyIdAluno(resgateDTO.getId_aluno());
        Empresa empresa = empresaService.findbyIdEmpresa(vantagem.getEmpresa().getId());

        if (aluno == null || vantagem == null) {
            return new ResponseEntity<>("Vantagem ou Aluno não existem", HttpStatusCode.valueOf(400));
        }

        double creditosVantagem = vantagem.getValor();
        double creditosAluno = aluno.getSaldo();

        if (creditosVantagem > creditosAluno) {
            return new ResponseEntity<>("Aluno não possui crédito suficiente", HttpStatusCode.valueOf(400));
        }

        double cresitosEmpresa = empresa.getSaldo();

        aluno.setSaldo((creditosAluno - creditosVantagem));
        empresa.setSaldo(cresitosEmpresa + creditosVantagem);

        Resgate resgate = new Resgate();
        resgate.setDescription(resgateDTO.getDescription());
        resgate.setData(resgateDTO.getData());
        resgate.setAluno(aluno);
        resgate.setVantagem(vantagem);
        resgate.setValor(creditosVantagem);

        vantagem.getResgates().add(resgate);

        resgateRepository.save(resgate);
        alunoRepository.salvar(aluno);
        empresaRepository.save(empresa);
        emailSenderStructureService.backGroudSenderResgate(aluno, empresa, vantagem);

        return ResponseEntity.ok(resgate);
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
