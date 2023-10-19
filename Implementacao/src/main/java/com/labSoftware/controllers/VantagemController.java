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

import com.labSoftware.DTO.VantagemDTO;
import com.labSoftware.models.Vantagem;
import com.labSoftware.models.Vantagem.CreateVantagem;
import com.labSoftware.models.Vantagem.UpdateVantagem;
import com.labSoftware.services.EmpresaService;
import com.labSoftware.services.VantagemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vantagem")
public class VantagemController {

    @Autowired
    private VantagemService vantagemService;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/{id}")
    public ResponseEntity<Vantagem> findbyIdVantagem(@PathVariable Long id) {
        Vantagem vantagem = vantagemService.findbyIdVantagem(id);

        if (vantagem == null) {
            return ResponseEntity.notFound().build();
        }

        // Converte a entidade Vantagem em um objeto VantagemDTO
        VantagemDTO vantagemDTO = new VantagemDTO(
                vantagem.getId(),
                vantagem.getFoto(),
                vantagem.getDescricao(),
                vantagem.getValor(),
                vantagem.getNome());

        return ResponseEntity.ok(vantagem);
    }

    @PostMapping("/create")
    @Validated(CreateVantagem.class)
    public ResponseEntity<Vantagem> createVantagem(@Valid @RequestBody Vantagem obj) {
        Vantagem vantagem = vantagemService.createVantagem(obj);

        // Converte a entidade Vantagem em um objeto VantagemDTO
        VantagemDTO vantagemDTO = new VantagemDTO(
                vantagem.getId(),
                vantagem.getFoto(),
                vantagem.getDescricao(),
                vantagem.getValor(),
                vantagem.getNome());

        return ResponseEntity.status(HttpStatus.CREATED).body(vantagem);
    }

    @PutMapping("/update/{id}")
    @Validated(UpdateVantagem.class)
    public ResponseEntity<Vantagem> updateVantagem(@Valid @RequestBody Vantagem obj, @PathVariable Long id) {
        obj.setId(id);
        Vantagem vantagem = vantagemService.updateVantagem(obj);

        // Converte a entidade Vantagem atualizada em um objeto VantagemDTO
        VantagemDTO vantagemDTO = new VantagemDTO(
                vantagem.getId(),
                vantagem.getFoto(),
                vantagem.getDescricao(),
                vantagem.getValor(),
                vantagem.getNome());

        return ResponseEntity.ok(vantagem);
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

    @GetMapping("/getByEmpresaId/{id}")
    public ResponseEntity<List<Vantagem>> getVantagensByEmpresa(@PathVariable Long id) {
        List<Vantagem> vantagens = vantagemService.getAllByEmpresaId(id);

        // // Converte as entidades Vantagem em uma lista de objetos VantagemDTO
        // List<VantagemDTO> vantagemDTOs = vantagens.stream()
        // .map(vantagem -> new VantagemDTO(
        // vantagem.getId(),
        // vantagem.getFoto(),
        // vantagem.getDescricao(),
        // vantagem.getValor(),
        // vantagem.getNome()))
        // .collect(Collectors.toList());

        return ResponseEntity.ok(vantagens);
    }
}
