package com.labSoftware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.labSoftware.models.Empresa;

public interface IEmpresaJpaRepository extends JpaRepository<Empresa, String> {

}
