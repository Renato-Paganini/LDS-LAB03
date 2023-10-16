package com.labSoftware.models;

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
public class Vantagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnpj_empresa")
    private Empresa empresa;

    @Column
    private String foto;

    @Column
    private String descricao;

    @Column
    private Double valor;

    @Column
    private String nome;

    public Vantagem(Long id, String descricao, String foto, Double valor) {
        this.id = id;
        this.descricao = descricao;
        this.foto = foto;
        this.valor = valor;
    }

    public Vantagem(String descricao, Double valor, String foto, String nome, Empresa empresa) {
        this.descricao = descricao;
        this.valor = valor;
        this.empresa = empresa;
        this.foto = foto;
        this.nome = nome;
    }


}
