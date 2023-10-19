package com.labSoftware.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.labSoftware.models.Empresa;
import com.labSoftware.models.Vantagem;
import com.labSoftware.repositories.EmpresaRepository;
import com.labSoftware.repositories.IVantagemJpaRepository;

@Service
public class VantagemService {

    private final IVantagemJpaRepository vantagemRepository;

    private final EmpresaRepository empresaRepository;

    public VantagemService(IVantagemJpaRepository vantagemRepository, EmpresaRepository empresaRepository) {
        this.vantagemRepository = vantagemRepository;
        this.empresaRepository = empresaRepository;
    }

    public ResponseEntity<?> criarVantagem(Vantagem v) {

        if (v == null) {

            return ResponseEntity.badRequest().body("Vantagem está nula");

        }

        Empresa empresa = empresaRepository.getReferenceById(v.getId());

        if (empresa == null) {

            return ResponseEntity.badRequest().body("Empresa não existe");

        }

        vantagemRepository.save(v);

        return ResponseEntity.ok(v);
    }

    public ResponseEntity<?> retornaVantagemPorEmpresa(Long id_empresa) {

        Empresa empresa = empresaRepository.getReferenceById(id_empresa);

        if (empresa == null) {
            return ResponseEntity.badRequest().body("Empresa não existe");
        }

        LinkedList<Vantagem> vantagens = vantagemRepository.retornaVantagensPelaEmpresa(id_empresa);

        return ResponseEntity.ok(vantagens);

    }

    public ResponseEntity<?> retornaTodasVantagens() {

        List<Vantagem> vantagens = vantagemRepository.findAll();

        return ResponseEntity.ok().body(vantagens);

    }

}
