package com.labSoftware.controllers;

import java.util.List;

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

import com.labSoftware.models.Empresa;
import com.labSoftware.models.Empresa.CreateEmpresa;
import com.labSoftware.models.Empresa.UpdateEmpresa;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<Empresa>> findAllEmpresa() {
        List<Empresa> obj = this.empresaService.getAll();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/auth")
    public ResponseEntity<Empresa> login(@RequestBody Empresa obj) throws Exception {
        Empresa alunoAutenticado = this.empresaService.login(obj);
        return ResponseEntity.ok().body(alunoAutenticado);
    }

    @PostMapping("/create")
    @Validated(CreateEmpresa.class)
    public ResponseEntity<Object> createEmpresa(@Valid @RequestBody Empresa obj) {
        try {
            return new ResponseEntity<Object>(this.empresaService.createEmpresa(obj), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    @Validated(UpdateEmpresa.class)
    public ResponseEntity<Void> updateEmpresa(@Valid @RequestBody Empresa obj, @PathVariable Long id) {
        obj.setId(id);
        this.empresaService.updateEmpresa(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id) {
        this.empresaService.deleteEmpresa(id);
        return ResponseEntity.noContent().build();
    }

}
