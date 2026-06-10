package com.leandra.LivroService.business.dto.Response;

import com.leandra.LivroService.infrastructure.Enums.StatusLivro;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroResponseDTO {
    private Long id;
    private String titulo;
    private String isbn;
    private String descricao;
    private String editora;
    private Integer anoPublicacao;
    private List<CategoriaResponseDTO> categorias;
    private Integer quantidadeTotal;
    private StatusLivro status;
    private List<AutorResponseDTO> autores;
}
