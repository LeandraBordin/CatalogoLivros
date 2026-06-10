package com.leandra.LivroService.controller;


import com.leandra.LivroService.business.LivroService;
import com.leandra.LivroService.business.dto.LivroDTO;
import com.leandra.LivroService.business.dto.Request.LivroRequestDTO;
import com.leandra.LivroService.business.dto.Response.LivroResponseDTO;
import com.leandra.LivroService.infrastructure.entity.Livro;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;
    @PostMapping
    public ResponseEntity<LivroResponseDTO> salvaLivro(@RequestBody LivroRequestDTO livroDTO){
        return ResponseEntity.ok(livroService.salvaLivro(livroDTO));
    }
}
