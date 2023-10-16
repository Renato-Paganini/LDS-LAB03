package com.labSoftware.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labSoftware.models.Aluno;
import com.labSoftware.repositories.AlunoRepository;

import jakarta.transaction.Transactional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno findbyIdAluno(Long id) {
        Optional<Aluno> aluno = this.alunoRepository.buscarPeloId(id);

        return aluno.orElseThrow(
                () -> new RuntimeException("Aluno não encontrado" + id + "Tipo: " + Aluno.class.getName()));
    }

    public Aluno login(Aluno obj) throws Exception {
        Aluno a = this.alunoRepository.buscarAlunoPeloCpf(obj.getCpf());
        if (obj.getSenha().equals(a.getSenha())) {
            Aluno aluno = this.alunoRepository.buscarAlunoPeloCpf(obj.getCpf());
            return aluno;
        } else {
            throw new Exception("Senha invalida");
        }
    }

    @Transactional
    public Aluno createAluno(Aluno obj) {
        obj.setId(null);
        obj = this.alunoRepository.salvar(obj);
        return obj;
    }

    @Transactional
    public Aluno updateAluno(Aluno obj) {
        Aluno aluno = findbyIdAluno(obj.getId());
        aluno.setCpf(obj.getCpf());
        aluno.setCurso(obj.getCurso());
        aluno.setEndereco(obj.getEndereco());
        aluno.setInstituicao(obj.getInstituicao());
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

    public Aluno trocarVantagem(Long id, Integer moedas) {
        Aluno aluno = alunoRepository.buscarPeloId(id).get();
        aluno.setSaldo(aluno.getSaldo() - moedas);
        return alunoRepository.salvar(aluno);
    }
}
