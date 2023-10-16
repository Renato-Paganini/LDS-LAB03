package com.labSoftware.mapper;

import com.labSoftware.dtos.VantagemDTO;
import com.labSoftware.models.Empresa;
import com.labSoftware.models.Vantagem;

public class VantagemMapper {

    public static Vantagem vantagemMapper(VantagemDTO vantagemDTO, Empresa empresa){

        return new Vantagem(

                vantagemDTO.descricao(),
                vantagemDTO.valor(),
                vantagemDTO.foto(),
                vantagemDTO.nome(),
                empresa

        );

    }

}
