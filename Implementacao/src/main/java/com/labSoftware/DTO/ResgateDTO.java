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
public class ResgateDTO {
    private LocalDate data;
    private Long id_vantagem;
    private Long id_aluno;
    private Long id_professor;
    private String description;

}
