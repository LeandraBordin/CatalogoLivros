package com.leandra.LivroService.controller;

import com.leandra.LivroService.business.AutorService;
import com.leandra.LivroService.business.dto.Request.AutorRequestDTO;
import com.leandra.LivroService.business.dto.Response.AutorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Não Autorizado: Token ausente, inválido ou expirado", content = @Content),
        @ApiResponse(responseCode = "403", description = "Proibido: O usuário não tem o cargo necessário", content = @Content)
})
@RequiredArgsConstructor
@Tag(name = "Autor", description = "Endpoints de gerenciamento de Autores")
public class AutorController {
    private final AutorService autorService;

    @PostMapping
    @Operation(summary = "Salva novo autor", description = "Cadastra um novo autor. Retorna 409 se já existir um autor com o mesmo nome.")
    @ApiResponse(responseCode = "201", description = "Autor salvo com sucesso!")
    @ApiResponse(responseCode = "409", description = "Autor já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<AutorResponseDTO> salvarAutor(@RequestBody AutorRequestDTO autorRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.salvarAutor(autorRequestDTO));
    }
    @GetMapping
    @Operation(summary = "Lista autores", description = "Retorna todos os autores cadastrados. Retorna lista vazia se não houver nenhum.")
    @ApiResponse(responseCode = "200", description = "Autores listados com sucesso!")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<List<AutorResponseDTO>> listarAutores(){
        return ResponseEntity.ok(autorService.listarAutores());
    }

    @GetMapping(params = "nome")
    @Operation(summary = "Busca autores por nome", description = "Retorna os autores correspondentes ao nome informado.")
    @ApiResponse(responseCode = "200", description = "Autores encontrados com sucesso!")
    @ApiResponse(responseCode = "404", description = "Nenhum autor encontrado com o nome informado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<List<AutorResponseDTO>> buscaAutorPorNome(@RequestParam String nome){
        return ResponseEntity.ok(autorService.buscaAutorPorNome(nome));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca autor por id", description = "Retorna o autor correspondente ao id informado.")
    @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<AutorResponseDTO> buscaAutorPorId(@PathVariable Long id){
        return ResponseEntity.ok(autorService.buscaAutorPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza autor", description = "Atualiza os dados do autor identificado pelo id. Os campos enviados substituem os valores atuais.")
    @ApiResponse(responseCode = "200", description = "Autor atualizado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<AutorResponseDTO> atualizaAutor(@PathVariable Long id, @RequestBody AutorRequestDTO autorRequestDTO){
        return ResponseEntity.ok(autorService.atualizaAutor(id, autorRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta autor por id", description = "Remove permanentemente o autor correspondente ao id informado.")
    @ApiResponse(responseCode = "204", description = "Autor deletado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    @ApiResponse(responseCode = "409",  description = "Conflito: o autor possui livros vinculados e não pode ser excluído")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> deletaAutor(@PathVariable Long id){
        autorService.deletaAutor(id);
        return ResponseEntity.noContent().build();
    }
}
