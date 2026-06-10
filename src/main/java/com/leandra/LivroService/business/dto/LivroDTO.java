package com.leandra.LivroService.business.dto;

import com.leandra.LivroService.infrastructure.Enums.StatusLivro;
import com.leandra.LivroService.infrastructure.entity.Autor;
import com.leandra.LivroService.infrastructure.entity.Categoria;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroDTO {
        private Long id;
        private String titulo;
        private String isbn;
        private String descricao;
        private String editora;
        private Integer anoPublicacao;
        private List<Categoria> categorias;
        private Integer quantidadeTotal;
        private StatusLivro status;
        private List<Autor> autores;
    }