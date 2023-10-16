package com.labSoftware.dtos;

import java.util.Date;

public record TransacaoDTO(
        Long id_professor,
        Long id_aluno,
        Date data,
        double valor
) {
}
