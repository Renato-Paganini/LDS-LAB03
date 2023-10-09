package com.labSoftware.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Aluno {

    public interface CreateAluno {

    }

    public interface UpdateAluno {

    }

    @Id
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
