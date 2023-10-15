package com.labSoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labSoftware.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
