package com.leandra.LivroService.business.dto.Request;

import com.leandra.LivroService.infrastructure.Enums.StatusLivro;
import com.leandra.LivroService.infrastructure.entity.Autor;
import com.leandra.LivroService.infrastructure.entity.Categoria;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroRequestDTO {
    private String titulo;
    private String isbn;
    private String descricao;
    private String editora;
    private Integer anoPublicacao;
    private List<Long> categoriasIds;
    private Integer quantidadeTotal;
    private List<Long> autoresIds;
}
