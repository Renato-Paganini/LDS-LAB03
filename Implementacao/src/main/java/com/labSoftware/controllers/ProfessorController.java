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

import com.labSoftware.DTO.ProfessorResponse;
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
    public ResponseEntity<Professor> findByIdProfessor(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(professorService.findByIdProfessor(id), HttpStatus.OK);
        } catch (Exception e) {
            // Adição de uma mensagem de log para registrar a exceção
            log.error("Erro ao buscar professor por ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<ProfessorResponse> login(@RequestBody Professor request) throws Exception {
        Professor professor = professorService.login(request);

        // Mapeie o objeto Professor para ProfessorResponse
        ProfessorResponse response = new ProfessorResponse(
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getCpf(),
                professor.getDepartamento(),
                professor.getSaldo(),
                professor.getInstituicao());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    @Validated(CreateProfessor.class)
    public ResponseEntity<Object> createProfessor(@Valid @RequestBody Professor obj) {
        try {
            return new ResponseEntity<Object>(this.professorService.insertProfessor(obj), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    @Validated(UpdateProfessor.class)
    public ResponseEntity<Void> updateProfessor(@Valid @RequestBody Professor obj, @PathVariable Long id) {
        obj.setId(id);
        this.professorService.updateProfessor(obj);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}/saldo")
    @Validated(UpdateProfessor.class)
    public ResponseEntity<Void> updateSaldoProfessor(@Valid @RequestBody Professor obj, @PathVariable Long id) {
        Professor p = professorService.findByIdProfessor(id);
        Double sal = p.getSaldo();
        professorService.darSaldoProfessor(id, sal);
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
