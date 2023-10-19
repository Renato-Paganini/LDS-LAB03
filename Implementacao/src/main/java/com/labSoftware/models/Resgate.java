package com.labSoftware.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Resgate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resgate", unique = true)
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_professor")
    private Professor professor;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vantagem")
    private Vantagem vantagem;

    @Temporal(TemporalType.TIMESTAMP) // Isso indica que o campo é uma data e hora
    @Column(name = "timestamp")
    private Date timestamp;
}
