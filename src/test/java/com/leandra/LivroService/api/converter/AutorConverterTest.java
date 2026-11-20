package com.leandra.LivroService.api.converter;

import com.leandra.LivroService.api.request.AutorRequestDTOFixture;
import com.leandra.LivroService.business.converter.AutorConverter;
import com.leandra.LivroService.business.dto.Request.AutorRequestDTO;
import com.leandra.LivroService.business.dto.Response.AutorResponseDTO;
import com.leandra.LivroService.infrastructure.entity.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AutorConverterTest {

    @InjectMocks

    AutorConverter autorConverter;
    Autor autor;
    AutorRequestDTO autorRequestDTO;

    @BeforeEach
    public void setup() {
        autor = Autor.builder().nome("J. R. R. Tolkien").nacionalidade("britânica").build();
        autorRequestDTO = AutorRequestDTOFixture.build("J. R. R. Tolkien", "britânica");
    }

    @Test
    void deveConverterParaAutor() {
        Autor entity = autorConverter.paraAutor(autorRequestDTO);
        assertEquals(autor, entity);
    }

    @Test
    void deveConverterParaAutorResponseDTO() {
        AutorResponseDTO esperado = AutorResponseDTO.builder()
                .id(autor.getId())
                .nome("J. R. R. Tolkien")
                .nacionalidade("britânica")
                .build();
        AutorResponseDTO entity = autorConverter.paraAutorResponseDTO(autor);
        assertEquals(esperado, entity);
    }

    @Test
    void deveAtualizarAutor() {
        Autor entity = Autor.builder()
                .id(1L)
                .nome("J. R. R. Tolkien")
                .nacionalidade("britânica")
                .build();
        AutorRequestDTO requestDTO = AutorRequestDTO.builder().nome("John Ronald Reuel Tolkien")
                .nacionalidade("Britânica")
                .build();

        Autor esperado = Autor.builder()
                .id(1L)
                .nome("John Ronald Reuel Tolkien")
                .nacionalidade("Britânica")
                .build();

        Autor resultado = autorConverter.updateAutor(requestDTO, entity);

        assertNotNull(resultado);
        assertEquals(esperado, resultado);
    }
}
