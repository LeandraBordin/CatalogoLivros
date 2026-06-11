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
    @GetMapping("/{isbn}")
    public ResponseEntity<LivroResponseDTO> buscaLivroPorIsbn(@PathVariable String isbn){
        return ResponseEntity.ok(livroService.buscaLivroPorIsbn(isbn));
    }
    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<LivroResponseDTO>> buscaLivroPorAutor(@PathVariable String autor){
        return ResponseEntity.ok(livroService.buscaLivroPorAutor(autor));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<LivroResponseDTO>> buscaLivroPorcategoria(@PathVariable String categoria){
        return ResponseEntity.ok(livroService.buscaLivroPorCategoria(categoria));
    }
}
