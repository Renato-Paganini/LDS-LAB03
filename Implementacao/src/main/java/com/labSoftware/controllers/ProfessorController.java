package com.labSoftware.controllers;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.labSoftware.models.Professor;
import com.labSoftware.models.Professor.CreateProfessor;
import com.labSoftware.models.Professor.UpdateProfessor;
import com.labSoftware.services.ProfessorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    ProfessorService professorService;

    @GetMapping("{id}")
    public ResponseEntity<Professor> findbyIdProfessor(@PathVariable Long id) {
        try {
            return new ResponseEntity<Professor>(this.professorService.findByIdProfessor(id), HttpStatus.OK);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/auth")
    public ResponseEntity<Professor> login(@RequestBody Professor obj) throws Exception {
        Professor alunoAutenticado = this.professorService.login(obj);
        return ResponseEntity.ok().body(alunoAutenticado);
    }

    @PostMapping("/create")
    @Validated(CreateProfessor.class)
    public ResponseEntity<Professor> createProfessor(@Valid @RequestBody Professor obj) {
        this.professorService.insertProfessor(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update/{id}")
    @Validated(UpdateProfessor.class)
    public ResponseEntity<Void> updateProfessor(@Valid @RequestBody Professor obj, @PathVariable Long id) {
        obj.setId(id);
        this.professorService.updateProfessor(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        this.professorService.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Professor>> findAllProfessor() {
        List<Professor> obj = this.professorService.getAll();
        return ResponseEntity.ok().body(obj);
    }
}
