package com.labSoftware.repositories;

import com.labSoftware.models.Empresa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpresaRepository {

    private final IEmpresaJpaRepository empresaJpaRepository;

    @Autowired
    public EmpresaRepository(IEmpresaJpaRepository empresaJpaRepository) {
        this.empresaJpaRepository = empresaJpaRepository;
    }

    public Empresa salvar(Empresa empresa) {
        return this.empresaJpaRepository.save(empresa);
    }

    public List<Empresa> buscarEmpresas() {
        return empresaJpaRepository.findAll();
    }

    public void deletarEmpresaPeloCnpj(String cnpj) {
        empresaJpaRepository.deleteById(cnpj);
    }

    public Optional<Empresa> buscarEmpresaPeloCnpj(String cnpj) {
        return empresaJpaRepository.findById(cnpj);
    }

}
