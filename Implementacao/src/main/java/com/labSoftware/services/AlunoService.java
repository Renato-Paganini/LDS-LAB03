package com.labSoftware.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.labSoftware.DTO.AlunoResponse;
import com.labSoftware.models.Aluno;
import com.labSoftware.models.Instituicao;
import com.labSoftware.models.Professor;
import com.labSoftware.repositories.AlunoRepository;
import com.labSoftware.repositories.InstituicaoRepository;
import com.labSoftware.repositories.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    public Aluno findbyIdAluno(Long id) {
        Optional<Aluno> aluno = this.alunoRepository.buscarPeloId(id);
        return aluno.orElseThrow(
                () -> new RuntimeException("Aluno não encontrado" + id + "Tipo: " + Aluno.class.getName()));

        
    }

    public List<Aluno> getAll() {
        List<Aluno> lista = this.alunoRepository.buscarAlunos();
        return lista;
    }

    public AlunoResponse login(Aluno obj) throws Exception {
        Aluno a = this.alunoRepository.buscarAlunoPeloCpf(obj.getCpf());
        if (obj.getSenha().equals(a.getSenha())) {
            Aluno aluno = this.alunoRepository.buscarAlunoPeloCpf(obj.getCpf());
            AlunoResponse alunoResponse = new AlunoResponse();
            alunoResponse.setId(aluno.getId());
            alunoResponse.setNome(aluno.getNome());
            alunoResponse.setEmail(aluno.getEmail());
            alunoResponse.setCpf(aluno.getCpf());
            alunoResponse.setRg(aluno.getRg());
            alunoResponse.setEndereco(aluno.getEndereco());
            alunoResponse.setCurso(aluno.getCurso());
            alunoResponse.setSaldo(aluno.getSaldo());
            alunoResponse.setInstituicao(aluno.getInstituicao());
            return alunoResponse;
        } else {
            throw new Exception("Senha invalida");
        }
    }

    @Transactional
    public Aluno createAluno(Aluno obj) {
        System.out.println(obj);
        obj.setId(null);
        Instituicao instituicao = this.instituicaoRepository.findById(obj.getInstituicao().getId()).orElse(null);
        if (instituicao == null) {
            throw new RuntimeException("Instituição não encontrada");
        }
        obj.setInstituicao(instituicao);
        return this.alunoRepository.salvar(obj);
    }

    @Transactional
    public Aluno updateAluno(Aluno obj) {
        Aluno aluno = findbyIdAluno(obj.getId());
        if (obj.getInstituicao() != null) {
            Instituicao instituicao = this.instituicaoRepository.findById(obj.getInstituicao().getId()).orElse(null);
            if (instituicao == null) {
                throw new RuntimeException("Instituição não encontrada");
            }
            aluno.setInstituicao(instituicao);
        }
        aluno.setCpf(obj.getCpf());
        aluno.setCurso(obj.getCurso());
        aluno.setEndereco(obj.getEndereco());
        aluno.setRg(obj.getRg());
        aluno.setSenha(obj.getSenha());
        aluno.setEmail(obj.getEmail());
        aluno.setNome(obj.getNome());
        return this.alunoRepository.salvar(aluno);
    }

    public void deleteAluno(Long id) {
        Aluno aluno = findbyIdAluno(id);
        try {
            this.alunoRepository.deletarAlunoPeloId(aluno);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }

    public Aluno receberMoedas(Long id, Integer moedas) {
        Aluno aluno = alunoRepository.buscarPeloId(id).get();
        aluno.setSaldo(aluno.getSaldo() + moedas);
        return alunoRepository.salvar(aluno);
    }

    public Double verificarSaldo(Long id) {
        return alunoRepository.buscarPeloId(id).get().getSaldo();
    }

    public Aluno trocarVantagem(Long id, Double moedas) {
        Aluno aluno = alunoRepository.buscarPeloId(id).get();
        aluno.setSaldo(aluno.getSaldo() - moedas);
        return alunoRepository.salvar(aluno);
    }

    public ResponseEntity<List<AlunoResponse>> retornaListaAlunosByIdProfessor(String cpf) {
        Professor p = this.professorRepository.findByCpf(cpf);
        List<Aluno> alunos = alunoRepository.buscarAlunos();
        List<Aluno> alunosCompativeis = alunos.stream().filter((a) -> a.getInstituicao() == p.getInstituicao())
                .collect(Collectors.toList());

        // Mapeie os objetos Aluno para AlunoResponse
        List<AlunoResponse> responseList = alunosCompativeis.stream()
                .map(aluno -> new AlunoResponse(
                        aluno.getId(),
                        aluno.getNome(),
                        aluno.getEmail(),
                        aluno.getCpf(),
                        aluno.getRg(),
                        aluno.getEndereco(),
                        aluno.getCurso(),
                        aluno.getSaldo(),
                        aluno.getInstituicao()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

}
