package com.leandra.LivroService.business.converter;

import com.leandra.LivroService.business.dto.Request.AutorRequestDTO;
import com.leandra.LivroService.business.dto.Response.AutorResponseDTO;
import com.leandra.LivroService.infrastructure.entity.Autor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutorConverter {

    public AutorResponseDTO paraAutorResponseDTO(Autor autor){
        return AutorResponseDTO.builder()
                .id(autor.getId())
                .nome(autor.getNome())
                .nacionalidade(autor.getNacionalidade())
                .build();
    }
    public Autor paraAutor(AutorRequestDTO autorRequestDTO){
        return Autor.builder()
                .nome(autorRequestDTO.getNome())
                .nacionalidade(autorRequestDTO.getNacionalidade())
                .build();
    }
}
