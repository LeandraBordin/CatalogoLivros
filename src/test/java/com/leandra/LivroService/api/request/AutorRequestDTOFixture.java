package com.leandra.LivroService.api.request;

import com.leandra.LivroService.business.dto.Request.AutorRequestDTO;

public class AutorRequestDTOFixture {

    public static AutorRequestDTO build(
            String nome,
            String nacionalidade
    ) {
        return new AutorRequestDTO(nome, nacionalidade);
    }
}
