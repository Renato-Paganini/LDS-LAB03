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

import com.labSoftware.models.Instituicao;
import com.labSoftware.models.Instituicao.CreateInstituicao;
import com.labSoftware.models.Instituicao.UpdateInstituicao;
import com.labSoftware.services.InstituicaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/instituicao")
public class InstituicaoController {
    @Autowired
    InstituicaoService instituicaoService;

    @GetMapping("{id}")
    public ResponseEntity<Instituicao> findbyIdInstituicao(@PathVariable Long id) {
        try {
            return new ResponseEntity<Instituicao>(this.instituicaoService.findbyIdInstituicao(id), HttpStatus.OK);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/create")
    @Validated(CreateInstituicao.class)
    public ResponseEntity<Instituicao> createInstituicao(@Valid @RequestBody Instituicao obj) {
        this.instituicaoService.createInstituicao(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/update/{id}")
    @Validated(UpdateInstituicao.class)
    public ResponseEntity<Void> updateInstituicao(@Valid @RequestBody Instituicao obj, @PathVariable Long id) {
        obj.setId(id);
        this.instituicaoService.updateInstituicao(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstituicao(@PathVariable Long id) {
        this.instituicaoService.deleteInstituicao(id);
        return ResponseEntity.noContent().build();
    }
}
