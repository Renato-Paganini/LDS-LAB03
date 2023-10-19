package com.labSoftware.DTO;

public class VantagemDTO {
    private Long id;
    private String foto;
    private String descricao;
    private Double valor;
    private String nome;

    public VantagemDTO(Long id, String foto, String descricao, Double valor, String nome) {
        this.id = id;
        this.foto = foto;
        this.descricao = descricao;
        this.valor = valor;
        this.nome = nome;
    }

    // Getters e setters
}