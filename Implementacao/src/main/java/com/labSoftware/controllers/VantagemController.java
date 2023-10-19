package com.labSoftware.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labSoftware.models.Vantagem;
import com.labSoftware.services.VantagemService;

@RestController
@RequestMapping("/api/v1/vantagem/")
public class VantagemController {

    private final VantagemService VANTAGEM_SERVICE;

    public VantagemController(VantagemService vantagemService) {
        this.VANTAGEM_SERVICE = vantagemService;
    }

    @PostMapping("criarVantagem")
    public ResponseEntity<?> criarVantagem(@RequestBody Vantagem vantagem) {
        return VANTAGEM_SERVICE.criarVantagem(vantagem);
    }

    @GetMapping("retornaVantagemPorEmpresa/{idEmpresa}")
    public ResponseEntity<?> retornaVantagemPorEmpresa(
            @PathVariable Long idEmpresa) {

        return VANTAGEM_SERVICE.retornaVantagemPorEmpresa(idEmpresa);

    }

    @GetMapping("retornaTodasVantagens")
    public ResponseEntity<?> retornaTodasVantagens() {
        return VANTAGEM_SERVICE.retornaTodasVantagens();
    }

}
