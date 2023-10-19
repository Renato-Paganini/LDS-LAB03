package com.labSoftware.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labSoftware.models.Vantagem;
import com.labSoftware.repositories.VantagemRepository;

import jakarta.transaction.Transactional;

@Service
public class VantagemService {

    @Autowired
    private VantagemRepository vantagemRepository;

    public Vantagem findbyIdVantagem(Long id) {
        Optional<Vantagem> v = this.vantagemRepository.findById(id);
        return v.orElseThrow(
                () -> new RuntimeException("Vantagem não encontrada" + id + "Tipo: " + Vantagem.class.getName()));
    }

    public List<Vantagem> getAll() {
        List<Vantagem> lista = this.vantagemRepository.findAll();
        return lista;
    }

    @Transactional
    public Vantagem createVantagem(Vantagem obj) {
        obj.setId(null);
        obj = this.vantagemRepository.save(obj);
        return obj;
    }
}
