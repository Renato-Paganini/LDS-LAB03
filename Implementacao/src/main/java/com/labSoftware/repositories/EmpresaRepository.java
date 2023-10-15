package com.labSoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.labSoftware.models.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Query("SELECT e FROM Empresa e WHERE e.cnpj = :cnpj")
    Empresa findByCNPJ(@Param("cnpj") String cnpj);
}
