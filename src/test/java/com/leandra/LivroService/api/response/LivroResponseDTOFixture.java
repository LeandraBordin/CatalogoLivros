package com.leandra.LivroService.api.response;

import com.leandra.LivroService.business.dto.Request.LivroRequestDTO;
import com.leandra.LivroService.business.dto.Response.AutorResponseDTO;
import com.leandra.LivroService.business.dto.Response.CategoriaResponseDTO;
import com.leandra.LivroService.business.dto.Response.LivroResponseDTO;
import com.leandra.LivroService.infrastructure.Enums.StatusLivro;

import java.util.List;

public class LivroResponseDTOFixture {

    public static LivroResponseDTO build(
             Long id,
     String titulo,
     String isbn,
     String descricao,
     String editora,
     Integer anoPublicacao,
     List<CategoriaResponseDTO> categorias,
     Integer quantidadeTotal,
     StatusLivro status,
     List<AutorResponseDTO> autores,
     String capaUrl
    ) {
        return new LivroResponseDTO(id,titulo,isbn,descricao,editora,anoPublicacao,categorias,quantidadeTotal,status,autores,capaUrl);
    }
}
