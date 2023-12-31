package com.labSoftware.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.labSoftware.models.Aluno;

@Repository
public class AlunoRepository {

    @Autowired
    private IAlunoJpaRepository alunoJpaRepository;

    public Aluno salvar(Aluno aluno) {
        return alunoJpaRepository.save(aluno);
    }

    public List<Aluno> buscarAlunos() {
        return alunoJpaRepository.findAll();
    }

    public void deletarAlunoPeloId(Aluno aluno) {
        alunoJpaRepository.delete(aluno);
    }

    public Aluno buscarAlunoPeloCpf(String cpf) {
        return alunoJpaRepository.findByCpf(cpf);
    }

    public Optional<Aluno> buscarPeloId(Long id) {
        return alunoJpaRepository.findById(id);
    }
}