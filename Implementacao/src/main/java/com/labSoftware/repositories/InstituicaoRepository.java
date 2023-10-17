package com.labSoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labSoftware.models.Instituicao;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {

}
