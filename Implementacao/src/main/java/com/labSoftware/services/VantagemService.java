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
        // Aqui, você pode usar o método personalizado do seu repositório
        // para buscar todas as vantagens relacionadas à empresa com o ID fornecido.
        List<Vantagem> lista = vantagemRepository.findAllByEmpresaId(empresaId);
        return lista;
    }

    @Transactional
    public Vantagem createVantagem(Vantagem obj) {
        obj.setId(null);
        obj = this.vantagemRepository.save(obj);
        return obj;
    }

    @Transactional
    public Vantagem updateVantagem(Vantagem obj) {
        Vantagem v = findbyIdVantagem(obj.getId());

        v.setNome(obj.getNome());
        v.setFoto(obj.getFoto());
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
        Vantagem v = findbyIdVantagem(id);

        try {
            this.vantagemRepository.deleteById(v.getId());
        } catch (Exception exception) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }
}
