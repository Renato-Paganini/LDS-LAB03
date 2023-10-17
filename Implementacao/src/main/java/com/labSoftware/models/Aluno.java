package com.labSoftware.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@Table(name = "Aluno")
@EqualsAndHashCode(callSuper = false)
public class Aluno {

    public interface CreateAluno {

    }

    public interface UpdateAluno {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno", unique = true)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "CPF", unique = true)
    private String cpf;

    @Column(name = "senha")
    private String senha;

    @Column(name = "RG", unique = true)
    private long rg;

    @Column
    private String endereco;

    @Column
    private String curso;

    @Column
    private Double saldo;

    // Precisa adicionar uma instituição proxima sprint
    @Column
    private String instituicao;

}
