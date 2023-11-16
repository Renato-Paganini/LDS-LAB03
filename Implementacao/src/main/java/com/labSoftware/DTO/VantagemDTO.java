package com.labSoftware.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.ALWAYS)
public class VantagemDTO {
    private Long id_empresa;
    private String descricao;
    private Double valor;
    private String nome;
}
