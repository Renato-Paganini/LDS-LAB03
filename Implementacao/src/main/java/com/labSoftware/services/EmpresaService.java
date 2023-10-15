package com.labSoftware.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labSoftware.models.Empresa;
import com.labSoftware.repositories.EmpresaRepository;
import com.labSoftware.repositories.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    ProfessorRepository professorRepository;

    public Empresa findbyIdEmpresa(Long id) {
        Optional<Empresa> empresa = this.empresaRepository.findById(id);
        return empresa.orElseThrow(
                () -> new RuntimeException("Empresa não encontrado" + id + "Tipo: " + Empresa.class.getName()));
    }

    @Transactional
    public Empresa createEmpresa(Empresa obj) {
        obj.setId(null);
        obj = this.empresaRepository.save(obj);
        return obj;
    }

    @Transactional
    public Empresa updateEmpresa(Empresa obj) {
        Empresa e = findbyIdEmpresa(obj.getId());
        e.setCnpj(obj.getCnpj());
        e.setListaProdutos(obj.getListaProdutos());
        return this.empresaRepository.save(e);
    }

    public void deleteEmpresa(Long id) {
        Empresa e = findbyIdEmpresa(id);

        try {
            this.empresaRepository.deleteById(e.getId());
        } catch (Exception exception) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }
}
