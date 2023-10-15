package com.labSoftware.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.labSoftware.models.Aluno.UpdateAluno;
import com.labSoftware.models.Empresa;
import com.labSoftware.models.Empresa.CreateEmpresa;
import com.labSoftware.services.EmpresaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @GetMapping("{id}")
    public ResponseEntity<Empresa> findbyIdEmpresa(@PathVariable Long id) {
        try {
            return new ResponseEntity<Empresa>(this.empresaService.findbyIdEmpresa(id), HttpStatus.OK);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/auth")
    public ResponseEntity<Empresa> login(@RequestBody Empresa obj) {
        Empresa alunoAutenticado = this.empresaService.findbyCnpjEmpresa(obj.getCnpj());
        return ResponseEntity.ok().body(alunoAutenticado);
    }

    @PostMapping("/create")
    @Validated(CreateEmpresa.class)
    public ResponseEntity<Empresa> createEmpresa(@Valid @RequestBody Empresa obj) {
        this.empresaService.createEmpresa(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update/{id}")
    @Validated(UpdateAluno.class)
    public ResponseEntity<Void> updateAluno(@Valid @RequestBody Empresa obj, @PathVariable Long id) {
        obj.setId(id);
        this.empresaService.updateEmpresa(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        this.empresaService.deleteEmpresa(id);
        return ResponseEntity.noContent().build();
    }

}
