package com.leandra.LivroService.api.response;

import com.leandra.LivroService.business.dto.Request.CategoriaRequestDTO;
import com.leandra.LivroService.business.dto.Response.CategoriaResponseDTO;

public class CategoriaResponseDTOFixture {

    public static CategoriaResponseDTO build(
            Long id,
            String nome
    ) {
        return new CategoriaResponseDTO(id,nome);
    }
}
