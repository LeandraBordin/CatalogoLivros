package com.leandra.LivroService.controller;


import com.leandra.LivroService.business.LivroService;
import com.leandra.LivroService.business.dto.Request.LivroRequestDTO;
import com.leandra.LivroService.business.dto.Response.LivroResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
@Tag(name = "Livro", description = "Endpoints de gerenciamento de livros")

public class LivroController {

    private final LivroService livroService;

    @PostMapping
    @Operation(summary = "Salva novo livro", description = "Cadastra um novo livro. Retorna 409 se já existir um livro com o mesmo ISBN.")
    @ApiResponse(responseCode = "201", description = "Livro salvo com sucesso!")
    @ApiResponse(responseCode = "409", description = "Livro já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<LivroResponseDTO> salvaLivro(@RequestBody LivroRequestDTO livroDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.salvaLivro(livroDTO));
    }

    @GetMapping
    @Operation(summary = "Lista livros", description = "Retorna todos os livros cadastrados. Retorna lista vazia se não houver nenhum.")
    @ApiResponse(responseCode = "200", description = "Livros listados com sucesso!")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<List<LivroResponseDTO>> listarLivros(){
        return ResponseEntity.ok(livroService.listarLivros());
    }

    @GetMapping("/{isbn}")
    @Operation(summary = "Busca livro por ISBN", description = "Retorna o livro correspondente ao ISBN informado.")
    @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<LivroResponseDTO> buscaLivroPorIsbn(@PathVariable String isbn){
        return ResponseEntity.ok(livroService.buscaLivroPorIsbn(isbn));
    }

    @GetMapping("/busca")
    @Operation(summary = "Busca livros com filtros", description = "Busca livros filtrando por nome, autor e/ou categoria. Todos os parâmetros são opcionais e podem ser combinados.")
    @ApiResponse(responseCode = "200", description = "Livros encontrados com sucesso!")
    @ApiResponse(responseCode = "404", description = "Nenhum livro encontrado com os filtros informados")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<List<LivroResponseDTO>> buscaLivrosComFiltros(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String categoria){
        return ResponseEntity.ok(livroService.buscaLivrosComFiltros(nome, autor, categoria));
    }

    @PutMapping("/{isbn}")
    @Operation(summary = "Atualiza livro", description = "Atualiza os dados do livro identificado pelo ISBN. Os campos enviados substituem os valores atuais.")
    @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<LivroResponseDTO> atualizarLivro(@PathVariable String isbn, @RequestBody LivroRequestDTO livroRequestDTO){
        return ResponseEntity.ok(livroService.atualizarLivro(isbn, livroRequestDTO));
    }

    @DeleteMapping("/id/{id}")
    @Operation(summary = "Deleta livro por ID", description = "Remove permanentemente o livro correspondente ao ID informado.")
    @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> deletaLivroPorId(@PathVariable Long id){
        livroService.deletaLivroPorId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/isbn/{isbn}")
    @Operation(summary = "Deleta livro por ISBN", description = "Remove permanentemente o livro correspondente ao ISBN informado.")
    @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> deletaLivroPorIsbn(@PathVariable String isbn){
        livroService.deletaLivroPorIsbn(isbn);
        return ResponseEntity.noContent().build();
    }
}
