package main.java.com.labSoftware.controllers;

import java.net.URI;

import com.labSoftware.models.Aluno;

import main.java.com.labSoftware.services.AlunoService;

@RestController
@RequestMapping("/aluno")
@Validated
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping("/{cpf}")
    public ResponseEntity<Aluno> findbyIdAluno(@PathVariable String cpf) {
        Aluno obj = this.alunoService.findbyIdAluno(cpf);
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

    @PutMapping("/{cpf}")
    @Validated(UpdateAluno.class)
    public ResponseEntity<Void> updateAluno(@Valid @RequestBody Aluno obj, @PathVariable String cpf) {
        obj.setCpf(cpf);
        this.alunoService.updateAluno(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteAluno(@PathVariable String cpf) {
        this.alunoService.deleteAluno(cpf);
        return ResponseEntity.noContent().build();
    }
}
