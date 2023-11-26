package com.labSoftware.services;

import java.util.List;
import java.util.Optional;

import com.labSoftware.DTO.VantagemDTO;
import jakarta.persistence.EntityNotFoundException;
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
        Empresa empresa = empresaRepository.findById(empresaId).orElse(null);

        if (empresa == null) {
            throw new EntityNotFoundException("Empresa com ID " + empresaId + " não encontrada");
        }

        return vantagemRepository.findAllByEmpresaId(empresaId);
    }

    @Transactional
    public Vantagem createVantagem(VantagemDTO vantagemDTO) {
        Vantagem obj = new Vantagem();
        obj.setId(null);
        obj.setDescricao(vantagemDTO.getDescricao());
        obj.setValor(vantagemDTO.getValor());
        obj.setNome(vantagemDTO.getNome());
        obj.setImagem(vantagemDTO.getImagem());

        Long empresaId = vantagemDTO.getId_empresa();

        if (empresaId == null) {
            throw new RuntimeException("O ID da empresa associada à vantagem não pode ser nulo");
        }

        Empresa e = this.empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        e.getListaVantagens().add(obj);
        obj.setEmpresa(e);

        Vantagem novaVantagem = this.vantagemRepository.save(obj);

        return novaVantagem;
    }

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
        v.setImagem((obj.getImagem()));

        if (v.getResgates() == null || v.getResgates().isEmpty()) {
            return this.vantagemRepository.save(v);
        } else {
            Vantagem novaVantagem = new Vantagem();
            novaVantagem.setNome(v.getNome());
            novaVantagem.setDescricao(v.getDescricao());
            novaVantagem.setEmpresa(v.getEmpresa());
            novaVantagem.setImagem(v.getImagem());
            novaVantagem.setValor(v.getValor());

            Vantagem vantagemSalva = this.vantagemRepository.save(novaVantagem);

            return vantagemSalva;
        }
    }

    public void deleteVantagem(Long id) {
        try {
            Vantagem v = findbyIdVantagem(id);

            if (v == null) {
                throw new EntityNotFoundException("Vantagem com ID " + id + " não encontrada");
            }

            if (v.getResgates() == null || v.getResgates().isEmpty()) {
                this.vantagemRepository.deleteById(v.getId());
            } else {
                throw new RuntimeException("Não é possível excluir a vantagem porque ela está sendo usada como histórico de resgates");
            }
        } catch (Exception exception) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }
}
