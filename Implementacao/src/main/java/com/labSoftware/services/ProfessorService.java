package com.labSoftware.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labSoftware.models.Instituicao;
import com.labSoftware.models.Professor;
import com.labSoftware.repositories.InstituicaoRepository;
import com.labSoftware.repositories.ProfessorRepository;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    public Professor findByIdProfessor(Long id) {
        Optional<Professor> p = this.professorRepository.findById(id);
        return p.orElseThrow(
                () -> new RuntimeException("Professor não encontrado" + id + "Tipo: " + Professor.class.getName()));
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

    public Professor insertProfessor(Professor obj) {
        obj.setId(null);
        Instituicao instituicao = this.instituicaoRepository.findById(obj.getInstituicao().getId()).orElse(null);
        if (instituicao == null) {
            throw new RuntimeException("Instituição não encontrada");
        }
        obj = this.professorRepository.save(obj);
        return obj;
    }

    public Professor updateProfessor(Professor p) {
        Professor newProfessor = findByIdProfessor(p.getId());
        if (p.getInstituicao() != null) {
            Instituicao instituicao = this.instituicaoRepository.findById(p.getInstituicao().getId()).orElse(null);
            if (instituicao == null) {
                throw new RuntimeException("Instituição não encontrada");
            }
            newProfessor.setInstituicao(instituicao);
        }
        newProfessor.setCpf(p.getCpf());
        newProfessor.setNome(p.getNome());
        newProfessor.setEmail(p.getEmail());
        newProfessor.setSenha(p.getSenha());
        newProfessor.setDepartamento(p.getDepartamento());
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
