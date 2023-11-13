package com.labSoftware.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.labSoftware.DTO.VantagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/vantagem")
public class VantagemController {

    @Autowired
    private VantagemService vantagemService;

    @GetMapping("/{id}")
    public ResponseEntity<Vantagem> findbyIdVantagem(@PathVariable Long id) {
        Vantagem vantagem = vantagemService.findbyIdVantagem(id);

        if (vantagem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vantagem);
    }

    @PostMapping("/create")
    @Validated(CreateVantagem.class)
    public ResponseEntity<Vantagem> createVantagem(@Valid @RequestBody VantagemDTO obj) {
        Vantagem vantagem = vantagemService.createVantagem(obj);

        return ResponseEntity.status(HttpStatus.CREATED).body(vantagem);
    }

    @PutMapping("/update/{id}")
    @Validated(UpdateVantagem.class)
    public ResponseEntity<Vantagem> updateVantagem(@Valid @RequestBody Vantagem obj, @PathVariable Long id) {
        obj.setId(id);
        Vantagem vantagem = vantagemService.updateVantagem(obj);

        return ResponseEntity.ok(vantagem);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVantagens(@PathVariable Long id) {
        try {
            this.vantagemService.deleteVantagem(id);
            return ResponseEntity.ok("Vantagem excluída com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Vantagem>> findAllVantagens() {
            List<Vantagem> obj = this.vantagemService.getAll();
            if (obj.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(obj);
    }

    @GetMapping("/getByEmpresaId/{id}")
    public ResponseEntity<List<Vantagem>> getVantagensByEmpresa(@PathVariable Long id) {
        List<Vantagem> vantagens = vantagemService.getAllByEmpresaId(id);

        if (vantagens.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        // List<VantagemDTO> vantagemDTOs = vantagens.stream()
        //         .map(vantagem -> new VantagemDTO(
        //                 vantagem.getId(),
        //                 vantagem.getDescricao(),
        //                 vantagem.getValor(),
        //                 vantagem.getNome()))
        //         .collect(Collectors.toList());

        return ResponseEntity.ok(vantagens);
    }
}
