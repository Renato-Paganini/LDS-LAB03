package com.labSoftware.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.labSoftware.models.Vantagem;

@Repository
public class VantagemRepository {

    private final IVantagemJpaRepository vantagemJpaRepository;

    @Autowired
    public VantagemRepository(IVantagemJpaRepository vantagemJpaRepository) {
        this.vantagemJpaRepository = vantagemJpaRepository;
    }

    public Vantagem salvar(Vantagem vantagem) {
        return vantagemJpaRepository.save(vantagem);
    }

    public List<Vantagem> buscarVantagems() {
        return vantagemJpaRepository.findAll();
    }

    public void deletarVantagemPeloId(long id) {
        vantagemJpaRepository.deleteById(id);
    }

    public Optional<Vantagem> buscarVatagemPeloID(long id) {
        return vantagemJpaRepository.findById(id);
    }

}
