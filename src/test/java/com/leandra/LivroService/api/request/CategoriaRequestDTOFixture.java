package com.leandra.LivroService.api.request;

import com.leandra.LivroService.business.dto.Request.AutorRequestDTO;
import com.leandra.LivroService.business.dto.Request.CategoriaRequestDTO;

public class CategoriaRequestDTOFixture {

    public static CategoriaRequestDTO build(
            String nome
    ) {
        return new CategoriaRequestDTO(nome);
    }
}
