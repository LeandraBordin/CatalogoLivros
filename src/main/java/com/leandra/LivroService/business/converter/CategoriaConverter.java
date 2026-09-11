package com.leandra.LivroService.business.converter;


import com.leandra.LivroService.business.dto.Request.CategoriaRequestDTO;
import com.leandra.LivroService.business.dto.Response.CategoriaResponseDTO;
import com.leandra.LivroService.infrastructure.entity.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoriaConverter {

    public CategoriaResponseDTO paraCategoriaResponseDTO(Categoria categoria){
        return CategoriaResponseDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .build();
    }
    public Categoria paraCategoria(CategoriaRequestDTO categoriaRequestDTO){
        return Categoria.builder()
                .nome(categoriaRequestDTO.getNome())
                .build();
    }

    public Categoria updateCategoria(CategoriaRequestDTO categoriaRequestDTO, Categoria entity){
        return Categoria.builder()
                .id(entity.getId())
                .nome( categoriaRequestDTO != null ? categoriaRequestDTO.getNome() : entity.getNome())
                .build();
    }
}
