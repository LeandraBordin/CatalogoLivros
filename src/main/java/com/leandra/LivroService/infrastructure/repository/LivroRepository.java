package com.leandra.LivroService.infrastructure.repository;

import com.leandra.LivroService.infrastructure.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro,Long> {

    Optional<Livro> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    List<Livro> findByAutoresId(Long id);
    List<Livro> findByCategoriasId(Long id);
    List<Livro> findByTituloContainingIgnoreCase(String nome);
    @Query("SELECT DISTINCT l FROM Livro l " +
            "LEFT JOIN l.autores a " +
            "LEFT JOIN l.categorias c " +
            "WHERE (:nome IS NULL OR LOWER(l.titulo) LIKE LOWER(CONCAT('%', CAST(:nome AS string), '%'))) " +
            "AND (:autor IS NULL OR LOWER(a.nome) LIKE LOWER(CONCAT('%', CAST(:autor AS string), '%'))) " +
            "AND (:categoria IS NULL OR LOWER(c.nome) LIKE LOWER(CONCAT('%', CAST(:categoria AS string), '%')))")
    List<Livro> buscaLivrosComFiltros(
            @Param("nome") String nome,
            @Param("autor") String autor,
            @Param("categoria") String categoria);
}
