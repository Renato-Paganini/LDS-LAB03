package com.labSoftware.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labSoftware.models.Professor;
import com.labSoftware.repositories.ProfessorRepository;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;

    public Professor findByIdProfessor(Long id) {
        Optional<Professor> p = this.professorRepository.findById(id);
        return p.orElseThrow(
                () -> new RuntimeException("Empresa não encontrado" + id + "Tipo: " + Professor.class.getName()));
    }

    public Professor login(Professor obj) throws Exception {
        Professor a = this.professorRepository.findByCpf(obj.getCpf());
        if (obj.getSenha().equals(a.getSenha())) {
            Professor p = this.professorRepository.findByCpf(obj.getCpf());
            return p;
        } else {
            throw new Exception("Senha invalida");
        }
    }

    public Professor insertProfessor(Professor p) {
        p.setId(null);
        p = this.professorRepository.save(p);
        return p;
    }

    public Professor updateProfessor(Professor p) {
        Professor newProfessor = findByIdProfessor(p.getId());
        newProfessor.setCpf(p.getCpf());
        newProfessor.setNome(p.getNome());
        newProfessor.setEmail(p.getEmail());
        newProfessor.setSenha(p.getSenha());
        newProfessor.setDepartamento(p.getDepartamento());
        newProfessor.setInstituicao(p.getInstituicao());
        newProfessor.setSaldo(p.getSaldo());
        return this.professorRepository.save(newProfessor);
    }

    public List<Professor> getAll() {
        List<Professor> lista = this.professorRepository.findAll();
        return lista;
    }

    public void deleteProfessor(Long id) {
        Professor e = findByIdProfessor(id);

        try {
            this.professorRepository.deleteById(e.getId());
        } catch (Exception exception) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }
}
