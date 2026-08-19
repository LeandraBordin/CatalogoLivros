package com.leandra.LivroService.controller;


import com.leandra.LivroService.business.LivroService;
import com.leandra.LivroService.business.dto.LivroDTO;
import com.leandra.LivroService.business.dto.Request.LivroRequestDTO;
import com.leandra.LivroService.business.dto.Response.LivroResponseDTO;
import com.leandra.LivroService.infrastructure.entity.Livro;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;
    @PostMapping
    public ResponseEntity<LivroResponseDTO> salvaLivro(@RequestBody LivroRequestDTO livroDTO){
        return ResponseEntity.ok(livroService.salvaLivro(livroDTO));
    }
    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> listarLivros(){
        return ResponseEntity.ok(livroService.listarLivros());
    }
    @GetMapping("/{isbn}")
    public ResponseEntity<LivroResponseDTO> buscaLivroPorIsbn(@PathVariable String isbn){
        return ResponseEntity.ok(livroService.buscaLivroPorIsbn(isbn));
    }
    @GetMapping("/busca")
    public ResponseEntity<List<LivroResponseDTO>> buscaLivrosComFiltros(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String categoria){
        return ResponseEntity.ok(livroService.buscaLivrosComFiltros(nome,autor,categoria));
    }
    @PutMapping("/{isbn}")
    public ResponseEntity<LivroResponseDTO> atualizarLivro(@PathVariable String isbn, @RequestBody LivroRequestDTO livroRequestDTO){
        return ResponseEntity.ok(livroService.atualizarLivro(isbn,livroRequestDTO));
    }
}
