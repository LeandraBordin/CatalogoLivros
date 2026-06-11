package com.leandra.LivroService.infrastructure.repository;

import com.leandra.LivroService.infrastructure.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro,Long> {

    Optional<Livro> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    List<Livro> findByAutoresId(Long id);
    List<Livro> findByCategoriasId(Long id);
}
