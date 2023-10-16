package com.labSoftware.services;

import com.labSoftware.dtos.VantagemDTO;
import com.labSoftware.mapper.VantagemMapper;
import com.labSoftware.models.Empresa;
import com.labSoftware.models.Vantagem;
import com.labSoftware.repositories.EmpresaRepository;
import com.labSoftware.repositories.IVantagemJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class VantagemService {

    private final IVantagemJpaRepository vantagemRepository;

    private final EmpresaRepository empresaRepository;

    public VantagemService(IVantagemJpaRepository vantagemRepository, EmpresaRepository empresaRepository) {
        this.vantagemRepository = vantagemRepository;
        this.empresaRepository = empresaRepository;
    }

    public ResponseEntity<?> criarVantagem(VantagemDTO vantagemDTO){

        if(vantagemDTO == null){

            return ResponseEntity.badRequest().body("Vantagem está nula");

        }

        Empresa empresa = empresaRepository.getReferenceById(vantagemDTO.idEmpresa());

        if(empresa == null){

            return ResponseEntity.badRequest().body("Empresa não existe");

        }

        Vantagem vantagem = VantagemMapper.vantagemMapper(vantagemDTO, empresa);

        vantagemRepository.save(vantagem);

        return ResponseEntity.ok(vantagem);
    }

    public ResponseEntity<?> retornaVantagemPorEmpresa(Long id_empresa){

        Empresa empresa = empresaRepository.getReferenceById(id_empresa);

        if(empresa == null){
            return ResponseEntity.badRequest().body("Empresa não existe");
        }

        LinkedList<Vantagem> vantagens = vantagemRepository.retornaVantagensPelaEmpresa(id_empresa);

        return ResponseEntity.ok(vantagens);

    }

    public ResponseEntity<?> retornaTodasVantagens(){

        List<Vantagem> vantagens = vantagemRepository.findAll();

        return ResponseEntity.ok().body(vantagens);

    }


}
