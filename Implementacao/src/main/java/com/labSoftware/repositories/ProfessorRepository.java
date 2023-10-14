package com.labSoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labSoftware.models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
