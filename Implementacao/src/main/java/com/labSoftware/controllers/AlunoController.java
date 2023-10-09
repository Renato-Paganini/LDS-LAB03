package com.labSoftware.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.labSoftware.models.Aluno;
import com.labSoftware.models.Aluno.CreateAluno;
import com.labSoftware.models.Aluno.UpdateAluno;
import com.labSoftware.services.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")
@Validated
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findbyIdAluno(@PathVariable Long id) {
        Aluno obj = this.alunoService.findbyIdAluno(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Validated(CreateAluno.class)
    public ResponseEntity<Void> createAluno(@Valid @RequestBody Aluno obj) {
        this.alunoService.createAluno(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cpf}")
                .buildAndExpand(obj.getCpf())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(UpdateAluno.class)
    public ResponseEntity<Void> updateAluno(@Valid @RequestBody Aluno obj, @PathVariable Long id) {
        obj.setId(id);
        this.alunoService.updateAluno(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteAluno(@PathVariable String cpf) {
        this.alunoService.deleteAluno(cpf);
        return ResponseEntity.noContent().build();
    }
}
