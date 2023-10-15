package com.labSoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.labSoftware.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query("SELECT p FROM Professor p WHERE p.cpf = :cpf")
    Professor findByCpf(@Param("cpf") String cpf);
}
