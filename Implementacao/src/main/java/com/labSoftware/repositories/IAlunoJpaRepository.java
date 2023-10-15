package com.labSoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.labSoftware.models.Aluno;

public interface IAlunoJpaRepository extends JpaRepository<Aluno, Long> {
    @Query("SELECT a FROM Aluno a WHERE a.cpf = :cpf")
    Aluno findByCpf(@Param("cpf") String cpf);

}