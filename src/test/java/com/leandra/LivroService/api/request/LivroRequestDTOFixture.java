package com.leandra.LivroService.api.request;

import com.leandra.LivroService.business.dto.Request.CategoriaRequestDTO;
import com.leandra.LivroService.business.dto.Request.LivroRequestDTO;

import java.util.List;

public class LivroRequestDTOFixture {

    public static LivroRequestDTO build(
            String titulo,
            String isbn,
            String descricao,
            String editora,
            Integer anoPublicacao,
            List<Long> categoriasIds,
            Integer quantidadeTotal,
            List<Long> autoresIds
    ) {
        return new LivroRequestDTO(titulo, isbn, descricao, editora, anoPublicacao, categoriasIds, quantidadeTotal, autoresIds);
    }
}
