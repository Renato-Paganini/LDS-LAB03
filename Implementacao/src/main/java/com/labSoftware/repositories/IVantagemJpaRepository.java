package com.labSoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.labSoftware.models.Vantagem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.LinkedList;

public interface IVantagemJpaRepository extends JpaRepository<Vantagem, Long> {

    @Query( "SELECT V " +
            "FROM Vantagem V " +
            "INNER JOIN V.empresa E " +
            "ON E.id = V.empresa.id ")
    public LinkedList<Vantagem> retornaVantagensPelaEmpresa(@Param("id_empresa") Long id_empresa);

}
