package com.leandra.LivroService.infrastructure.entity;

import com.leandra.LivroService.infrastructure.Enums.StatusLivro;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo",length = 25,nullable = false)
    private String titulo;

    @Column(name = "isbn",length = 13,nullable = false,unique = true)
    private String isbn;

    @Column(name = "descricao", length = 100)
    private String descricao;

    @Column(name = "editora",length = 25,nullable = false)
    private String editora;

    @Column(name = "ano_publicacao",nullable = false)
    private Integer anoPublicacao;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "livro_categoria",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<>();

    @Column(name = "quantidade_total",nullable = false)
    private Integer quantidadeTotal;

    @Column(name = "quantidade_disponivel",nullable = false)
    private Integer quantidadeDisponivel;

    @Enumerated(EnumType.STRING)
    private StatusLivro status;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();


}
