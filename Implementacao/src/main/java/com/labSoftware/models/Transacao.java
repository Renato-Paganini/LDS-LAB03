package com.labSoftware.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Professor professor;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Aluno aluno;

    private double valor;

    private LocalDate data;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Vantagem vantagem;

    public Transacao(Professor professor, Aluno aluno, double valor, LocalDate data) {
        this.professor = professor;
        this.aluno = aluno;
        this.valor = valor;
        this.data = data;
    }
    public Transacao(Aluno aluno, LocalDate data,Vantagem vantagem, double valor) {
        this.vantagem = vantagem;
        this.aluno = aluno;
        this.valor = valor;
        this.data = data;
    }

}
