package com.labSoftware.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.labSoftware.models.Aluno;
import java.util.List;
import java.util.Optional;

@Repository
public class AlunoRepository {
    private final IAlunoJpaRepository alunoJpaRepository;

    @Autowired
    public AlunoRepository(IAlunoJpaRepository alunoJpaRepository) {
        this.alunoJpaRepository = alunoJpaRepository;
    }

    public Aluno salvar(Aluno aluno) {
        return alunoJpaRepository.save(aluno);
    }

    public List<Aluno> buscarAlunos() {
        return alunoJpaRepository.findAll();
    }

    public void deletarAlunoPeloCpf(String cpf) {
        alunoJpaRepository.deleteById(cpf);
    }

    public Optional<Aluno> buscarAlunoPeloCpf(String cpf) {
        return alunoJpaRepository.findById(cpf);
    }
}