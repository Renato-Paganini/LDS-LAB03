package com.labSoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.labSoftware.models.Aluno;

public interface IAlunoJpaRepository extends JpaRepository<Aluno, String> {
}