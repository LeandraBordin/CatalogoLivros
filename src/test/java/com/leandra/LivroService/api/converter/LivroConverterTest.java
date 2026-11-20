package com.leandra.LivroService.api.converter;

import com.leandra.LivroService.api.request.LivroRequestDTOFixture;
import com.leandra.LivroService.business.converter.AutorConverter;
import com.leandra.LivroService.business.converter.CategoriaConverter;
import com.leandra.LivroService.business.converter.LivroConverter;
import com.leandra.LivroService.business.dto.Request.LivroRequestDTO;
import com.leandra.LivroService.business.dto.Response.AutorResponseDTO;
import com.leandra.LivroService.business.dto.Response.CategoriaResponseDTO;
import com.leandra.LivroService.business.dto.Response.LivroResponseDTO;
import com.leandra.LivroService.infrastructure.Enums.StatusLivro;
import com.leandra.LivroService.infrastructure.entity.Autor;
import com.leandra.LivroService.infrastructure.entity.Categoria;
import com.leandra.LivroService.infrastructure.entity.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class LivroConverterTest {

    @InjectMocks
    LivroConverter livroConverter;
    Livro livro;
    Autor autor;
    Categoria categoria;
    LivroRequestDTO livroRequestDTO;
    @Spy
    AutorConverter autorConverter = new AutorConverter();
    @Spy
    CategoriaConverter categoriaConverter = new CategoriaConverter();

    @BeforeEach
    public void setup() {
         autor = Autor.builder()
                .id(1L)
                .nome("Robert C. Martin")
                .nacionalidade("Americana")
                .build();

         categoria = Categoria.builder()
                .id(1L)
                .nome("Programação")
                .build();

         livro = Livro.builder()
                 .id(1L)
                .titulo("Código Limpo")
                .isbn("9788576082675")
                .descricao("Guia prático sobre boas práticas de escrita e refatoração de código.")
                .editora("Alta Books")
                .anoPublicacao(2009)
                .quantidadeTotal(10)
                .quantidadeDisponivel(10)
                .status(StatusLivro.DISPONIVEL)
                .capaUrl(null)
                 .autores(List.of(autor))
                 .categorias(List.of(categoria))
                .build();

         livroRequestDTO = LivroRequestDTOFixture.build(
                "Código Limpo",
                "9788576082675",
                "Guia prático sobre boas práticas de escrita e refatoração de código.",
                "Alta Books",
                2009,
                List.of(autor.getId()),
                10,
                List.of(categoria.getId())
        );
    }

    @Test
    void deveConverterParaLivro() {
        Livro entity = livroConverter.paraLivro(livroRequestDTO);

        assertThat(entity)
                .usingRecursiveComparison()
                .ignoringFields("id", "autores", "categorias")
                .isEqualTo(livro);
    }

    @Test
    void deveConverterParaLivroResponseDTO() {
        LivroResponseDTO esperado = LivroResponseDTO.builder()
                .id(1L)
                .titulo("Código Limpo")
                .isbn("9788576082675")
                .descricao("Guia prático sobre boas práticas de escrita e refatoração de código.")
                .editora("Alta Books")
                .anoPublicacao(2009)
                .quantidadeTotal(10)
                .status(StatusLivro.DISPONIVEL)
                .capaUrl(null)
                .categorias(List.of(CategoriaResponseDTO.builder().id(1L).nome("Programação").build()))
                .autores(List.of(AutorResponseDTO.builder().id(1L).nome("Robert C. Martin").nacionalidade("Americana").build()))
                .build();

        LivroResponseDTO entity = livroConverter.paraLivroDTO(livro);
        assertEquals(esperado, entity);
    }
    @Test
    void deveAtualizarTodosOsCamposDoLivro() {
        LivroRequestDTO requestDTO = LivroRequestDTOFixture.build(
                "Arquitetura Limpa",
                "9788550804606",
                "Guia para projetar software com boas fronteiras entre camadas.",
                "Alta Books",
                2019,
                List.of(2L),
                15,
                List.of(2L)
        );

        Livro resultado = livroConverter.updateLivro(requestDTO, livro);

         assertEquals("Arquitetura Limpa", resultado.getTitulo());
        assertEquals("9788550804606", resultado.getIsbn());
        assertEquals("Guia para projetar software com boas fronteiras entre camadas.", resultado.getDescricao());
        assertEquals("Alta Books", resultado.getEditora());
        assertEquals(2019, resultado.getAnoPublicacao());
        assertEquals(15, resultado.getQuantidadeTotal());
    }
}
