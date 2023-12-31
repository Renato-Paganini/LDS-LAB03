package com.labSoftware.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Resgate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resgate", unique = true)
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vantagem")
    private Vantagem vantagem;

    private Double valor;

    @Column(name = "data")
    private LocalDate data;

    public Resgate(String description, LocalDate data, Aluno aluno, Vantagem vantagem, Double valor) {
        setDescription(description);
        setData(data);
        setAluno(aluno);
        setVantagem(vantagem);
        setValor(valor);
    }
}
