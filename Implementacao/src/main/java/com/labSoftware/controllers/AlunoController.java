package com.labSoftware.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getAll")
    public ResponseEntity<List<Aluno>> findAllAluno() {
        List<Aluno> obj = this.alunoService.getAll();
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/auth")
    public ResponseEntity<Aluno> login(@RequestBody Aluno obj) throws Exception {
        Aluno alunoAutenticado = this.alunoService.login(obj);
        return ResponseEntity.ok().body(alunoAutenticado);
    }

    @PostMapping
    @Validated(CreateAluno.class)
    public ResponseEntity<Object> createAluno(@Valid @RequestBody Aluno obj) {
        System.out.println(obj);
        // this.alunoService.createAluno(obj);
        // URI uri = ServletUriComponentsBuilder
        // .fromCurrentRequest()
        // .path("/{cpf}")
        // .buildAndExpand(obj.getCpf())
        // .toUri();
        // return ResponseEntity.created(uri).build();
        try {
            return new ResponseEntity<Object>(this.alunoService.createAluno(obj), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    @Validated(UpdateAluno.class)
    public ResponseEntity<Void> updateAluno(@Valid @RequestBody Aluno obj, @PathVariable Long id) {
        obj.setId(id);
        this.alunoService.updateAluno(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        this.alunoService.deleteAluno(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/trocarVantagem/{id}")
    public ResponseEntity<Void> trocarVantagem(@PathVariable Long id, @RequestBody Map<String, Double> jsonMap) {
        Double moedas = jsonMap.get("moedas");
        this.alunoService.trocarVantagem(id, moedas);
        return ResponseEntity.ok().build();
    }
}
