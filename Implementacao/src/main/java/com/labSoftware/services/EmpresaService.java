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

    public Empresa findbyCnpjEmpresa(String cnpj) {
        Empresa empresa = this.empresaRepository.findByCNPJ(cnpj);
        return empresa;
    }

    public Empresa login(Empresa obj) throws Exception {
        Empresa a = this.empresaRepository.findByCNPJ(obj.getCnpj());
        if (obj.getSenha().equals(a.getSenha())) {
            Empresa empresa = this.empresaRepository.findByCNPJ(obj.getCnpj());
            return empresa;
        } else {
            throw new Exception("Senha invalida");
        }
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
        e.setEmail(obj.getEmail());
        e.setNome(obj.getNome());
        e.setSenha(obj.getSenha());
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
