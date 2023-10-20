package com.labSoftware.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Professor {

    public interface CreateProfessor {

    }

    public interface UpdateProfessor {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_professor", unique = true)
    @JsonBackReference
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "saldo")
    private Double saldo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_instituicao")
    private Instituicao instituicao;
}
