package com.leandra.LivroService.api.response;

import com.leandra.LivroService.business.dto.Request.AutorRequestDTO;
import com.leandra.LivroService.business.dto.Response.AutorResponseDTO;

public class AutorResponseDTOFixture {

    public static AutorResponseDTO build(
            Long id,
            String nome,
            String nacionalidade
    ) {
        return new AutorResponseDTO(id,nome, nacionalidade);
    }
}
