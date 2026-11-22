package com.leandra.LivroService.api.converter;

import com.leandra.LivroService.api.request.AutorRequestDTOFixture;
import com.leandra.LivroService.api.request.CategoriaRequestDTOFixture;
import com.leandra.LivroService.business.converter.AutorConverter;
import com.leandra.LivroService.business.converter.CategoriaConverter;
import com.leandra.LivroService.business.dto.Request.AutorRequestDTO;
import com.leandra.LivroService.business.dto.Request.CategoriaRequestDTO;
import com.leandra.LivroService.business.dto.Response.AutorResponseDTO;
import com.leandra.LivroService.business.dto.Response.CategoriaResponseDTO;
import com.leandra.LivroService.infrastructure.entity.Autor;
import com.leandra.LivroService.infrastructure.entity.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CategoriaConverterTest {

    @InjectMocks

    CategoriaConverter categoriaConverter;
    Categoria categoria;
    CategoriaRequestDTO categoriaRequestDTO;

    @BeforeEach
    public void setup() {
        categoria = Categoria.builder().nome("Ficção").build();
        categoriaRequestDTO = CategoriaRequestDTOFixture.build("Ficção");
    }

    @Test
    void deveConverterParaCategoria() {
        Categoria entity = categoriaConverter.paraCategoria(categoriaRequestDTO);
        assertEquals(categoria, entity);
    }

    @Test
    void deveConverterParaCategoriaResponseDTO() {
        CategoriaResponseDTO esperado = CategoriaResponseDTO.builder()
                .id(categoria.getId())
                .nome("Ficção")
                .build();
        CategoriaResponseDTO entity = categoriaConverter.paraCategoriaResponseDTO(categoria);
        assertEquals(esperado, entity);
    }

    @Test
    void deveAtualizarCategoria() {
        Categoria entity = Categoria.builder()
                .id(1L)
                .nome("Ficção")
                .build();

        CategoriaRequestDTO requestDTO = CategoriaRequestDTO.builder().nome("Terror")
                .build();

        Categoria esperado = Categoria.builder()
                .id(1L)
                .nome("Terror")
                .build();

        Categoria resultado = categoriaConverter.updateCategoria(requestDTO, entity);

        assertNotNull(resultado);
        assertEquals(esperado, resultado);
    }
}
