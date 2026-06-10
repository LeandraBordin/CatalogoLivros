package com.leandra.LivroService.business.converter;

import com.leandra.LivroService.business.dto.LivroDTO;
import com.leandra.LivroService.business.dto.Request.LivroRequestDTO;
import com.leandra.LivroService.business.dto.Response.LivroResponseDTO;
import com.leandra.LivroService.infrastructure.Enums.StatusLivro;
import com.leandra.LivroService.infrastructure.entity.Autor;
import com.leandra.LivroService.infrastructure.entity.Livro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class LivroConverter {

    private final AutorConverter autorConverter;
    private final CategoriaConverter categoriaConverter;

    public Livro paraLivro(LivroRequestDTO livroDTO){
        return Livro.builder()
                .titulo(livroDTO.getTitulo())
                .isbn(livroDTO.getIsbn())
                .descricao(livroDTO.getDescricao())
                .editora(livroDTO.getEditora())
                .anoPublicacao(livroDTO.getAnoPublicacao())
                .quantidadeTotal(livroDTO.getQuantidadeTotal())
                .quantidadeDisponivel(livroDTO.getQuantidadeTotal())
                .status(StatusLivro.DISPONIVEL)
                .build();
    }

    public LivroResponseDTO paraLivroDTO(Livro livro){
        return LivroResponseDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .isbn(livro.getIsbn())
                .descricao(livro.getDescricao())
                .editora(livro.getEditora())
                .anoPublicacao(livro.getAnoPublicacao())
                .categorias(livro.getCategorias().stream()
                        .map(categoriaConverter::paraCategoriaResponseDTO)
                        .toList())
                .quantidadeTotal(livro.getQuantidadeTotal())
                .status(livro.getStatus())
                .autores(livro.getAutores().stream()
                        .map(autorConverter::paraAutorResponseDTO)
                        .toList())
                .build();
    }
}
