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

import com.labSoftware.models.Vantagem;
import com.labSoftware.models.Vantagem.CreateVantagem;
import com.labSoftware.models.Vantagem.UpdateVantagem;
import com.labSoftware.services.VantagemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vantagem")
public class VantagemController {

    @Autowired
    private VantagemService vantagemService;

    @GetMapping("{id}")
    public ResponseEntity<Object> findbyIdVantagem(@PathVariable Long id) {
        try {
            return new ResponseEntity<Object>(this.vantagemService.findbyIdVantagem(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    @Validated(CreateVantagem.class)
    public ResponseEntity<Object> createVantagem(@Valid @RequestBody Vantagem obj) {
        try {
            return new ResponseEntity<Object>(this.vantagemService.createVantagem(obj), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    @Validated(UpdateVantagem.class)
    public ResponseEntity<Void> updateVantagens(@Valid @RequestBody Vantagem obj, @PathVariable Long id) {
        obj.setId(id);
        this.vantagemService.updateVantagem(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVantagens(@PathVariable Long id) {
        this.vantagemService.deleteVantagem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Vantagem>> findAllVantagens() {
        List<Vantagem> obj = this.vantagemService.getAll();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/getByEmpresaId")
    public ResponseEntity<List<Vantagem>> findAllVantagensByIdEmpresa(@PathVariable Long id) {
        List<Vantagem> obj = this.vantagemService.getAllByEmpresaId(id);
        return ResponseEntity.ok().body(obj);
    }
}
