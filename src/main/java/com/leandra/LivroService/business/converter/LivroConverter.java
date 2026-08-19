package com.leandra.LivroService.business.converter;

import com.leandra.LivroService.business.dto.Request.LivroRequestDTO;
import com.leandra.LivroService.business.dto.Response.LivroResponseDTO;
import com.leandra.LivroService.infrastructure.Enums.StatusLivro;
import com.leandra.LivroService.infrastructure.entity.Livro;
import com.leandra.LivroService.infrastructure.repository.AutorRepository;
import com.leandra.LivroService.infrastructure.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LivroConverter {

    private final AutorConverter autorConverter;
    private final CategoriaConverter categoriaConverter;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

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

    public Livro updateLivro(LivroRequestDTO livroDTO, Livro entity){
        return Livro.builder()
                .id(entity.getId())
                .titulo(livroDTO.getTitulo() != null ? livroDTO.getTitulo() : entity.getTitulo())
                .isbn(livroDTO.getIsbn() != null ? livroDTO.getIsbn() : entity.getIsbn())
                .descricao(livroDTO.getDescricao() != null ? livroDTO.getDescricao() : entity.getDescricao())
                .editora(livroDTO.getEditora() != null ? livroDTO.getEditora() : entity.getEditora())
                .anoPublicacao(livroDTO.getAnoPublicacao() != null ? livroDTO.getAnoPublicacao() : entity.getAnoPublicacao())
                .categorias(entity.getCategorias())
                .autores(entity.getAutores())
                .quantidadeTotal(entity.getQuantidadeTotal())
                .quantidadeDisponivel(entity.getQuantidadeDisponivel())
                .status(entity.getStatus())
                .build();
    }
}
