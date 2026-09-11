package com.leandra.LivroService.controller;

import com.leandra.LivroService.business.AutorService;
import com.leandra.LivroService.business.dto.Request.AutorRequestDTO;
import com.leandra.LivroService.business.dto.Response.AutorResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {
    private final AutorService autorService;

    @PostMapping
    public ResponseEntity<AutorResponseDTO> salvarAutor(@RequestBody AutorRequestDTO autorRequestDTO){
        return ResponseEntity.ok(autorService.salvarAutor(autorRequestDTO));
    }
    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> listarAutores(){
        return ResponseEntity.ok(autorService.listarAutores());
    }
    @GetMapping(params = "nome")
    public ResponseEntity<List<AutorResponseDTO>> buscaAutorPorNome(@RequestParam String nome){
        return ResponseEntity.ok(autorService.buscaAutorPorNome(nome));
    }
    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> buscaAutorPorId(@PathVariable Long id){
        return ResponseEntity.ok(autorService.buscaAutorPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> atualizaAutor(@PathVariable Long id, @RequestBody AutorRequestDTO autorRequestDTO){
        return ResponseEntity.ok(autorService.atualizaAutor(id,autorRequestDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaAutor(@PathVariable Long id){
       autorService.deletaAutor(id);
       return ResponseEntity.noContent().build();
    }
}
