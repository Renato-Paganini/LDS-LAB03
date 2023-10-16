package com.labSoftware.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private Usuario usuario;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "RG")
    private long rg;

    @Column
    private String endereco;

    @Column
    private String curso;

    @Column
    private int saldo;

    // Precisa adicionar uma instituição proxima sprint
    @Column
    private String instituicao;

}
