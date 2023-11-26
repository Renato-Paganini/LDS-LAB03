package com.labSoftware.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Vantagem {

    public interface CreateVantagem {

    }

    public interface UpdateVantagem {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vantagem", unique = true)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @OneToMany(mappedBy = "vantagem", cascade = CascadeType.ALL)
    private List<Resgate> resgates;

    @Column
    private String descricao;

    @Column
    private Double valor;

    @Column
    private String nome;

    @Column String imagem;
}
