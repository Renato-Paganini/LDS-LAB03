package com.labSoftware.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labSoftware.models.Empresa;
import com.labSoftware.models.Vantagem;
import com.labSoftware.repositories.EmpresaRepository;
import com.labSoftware.repositories.VantagemRepository;

import jakarta.transaction.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class VantagemService {

    @Autowired
    private VantagemRepository vantagemRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public Vantagem findbyIdVantagem(Long id) {
        Optional<Vantagem> v = this.vantagemRepository.findById(id);
        return v.orElseThrow(
                () -> new RuntimeException("Vantagem não encontrada" + id + "Tipo: " + Vantagem.class.getName()));
    }

    public List<Vantagem> getAll() {
        List<Vantagem> lista = this.vantagemRepository.findAll();
        return lista;
    }

    public List<Vantagem> getAllByEmpresaId(Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId).orElse(null);

        if (empresa == null) {
            throw new EntityNotFoundException("Empresa com ID " + empresaId + " não encontrada");
        }

        return vantagemRepository.findAllByEmpresaId(empresaId);
    }

    @Transactional
    public Vantagem createVantagem(Vantagem obj) {
        obj.setId(null);
        Empresa e = this.empresaRepository.findById(obj.getEmpresa().getId()).orElse(null);
        if (e == null) {
            throw new RuntimeException("Empresa não encontrada");
        }
        obj.setEmpresa(e);
        return this.vantagemRepository.save(obj);
    }

    @Transactional
    public Vantagem updateVantagem(Vantagem obj) {
        Vantagem v = findbyIdVantagem(obj.getId());

        v.setNome(obj.getNome());
        v.setDescricao(obj.getDescricao());
        if (obj.getEmpresa() != null) {
            Empresa e = this.empresaRepository.findById(obj.getEmpresa().getId()).orElse(null);
            if (e == null) {
                throw new RuntimeException("Empresa não encontrada");
            }
            v.setEmpresa(e);
        }
        v.setValor(obj.getValor());
        return this.vantagemRepository.save(v);
    }

    public void deleteVantagem(Long id) {
        try {
            Vantagem v = findbyIdVantagem(id);

            if (v == null) {
                throw new EntityNotFoundException("Vantagem com ID " + id + " não encontrada");
            }
            this.vantagemRepository.deleteById(v.getId());
        } catch (Exception exception) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }
}
