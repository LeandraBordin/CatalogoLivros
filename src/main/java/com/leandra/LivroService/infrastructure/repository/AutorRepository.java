package com.leandra.LivroService.infrastructure.repository;

import com.leandra.LivroService.infrastructure.entity.Autor;
import com.leandra.LivroService.infrastructure.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {

    boolean existsByNome(String nome);

    Optional<Autor> findByNome(String nome);
    List<Autor> findByNomeContainingIgnoreCase(String nome);
}
