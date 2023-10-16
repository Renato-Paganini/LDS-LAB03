package com.labSoftware.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Professor professor;

    @OneToOne(fetch = FetchType.LAZY)
    private Aluno aluno;

    private double valor;

    private Date data;

    public Transacao(Professor professor, Aluno aluno, double valor, Date data) {
        this.professor = professor;
        this.aluno = aluno;
        this.valor = valor;
        this.data = data;
    }

}
