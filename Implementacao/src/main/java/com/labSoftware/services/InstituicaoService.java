package com.labSoftware.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labSoftware.models.Instituicao;
import com.labSoftware.repositories.InstituicaoRepository;

import jakarta.transaction.Transactional;

@Service
public class InstituicaoService {

    @Autowired
    InstituicaoRepository instituicaoRepository;

    public Instituicao findbyIdInstituicao(Long id) {
        Optional<Instituicao> i = this.instituicaoRepository.findById(id);
        return i.orElseThrow(
                () -> new RuntimeException("Empresa não encontrado" + id + "Tipo: " + Instituicao.class.getName()));
    }

    public List<Instituicao> getAll() {
        List<Instituicao> lista = this.instituicaoRepository.findAll();
        return lista;
    }

    @Transactional
    public Instituicao createInstituicao(Instituicao obj) {
        obj.setId(null);
        obj = this.instituicaoRepository.save(obj);
        return obj;
    }

    @Transactional
    public Instituicao updateInstituicao(Instituicao obj) {
        Instituicao i = findbyIdInstituicao(obj.getId());
        i.setNome(obj.getNome());
        return this.instituicaoRepository.save(i);
    }

    public void deleteInstituicao(Long id) {
        Instituicao e = findbyIdInstituicao(id);

        try {
            this.instituicaoRepository.deleteById(e.getId());
        } catch (Exception exception) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }

}
