package com.labSoftware.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.ALWAYS)
public class DepositoDTO {
    private LocalDate data;
    private double valor;
    private Long id_aluno;
    private Long id_professor;
    private String description;
}
