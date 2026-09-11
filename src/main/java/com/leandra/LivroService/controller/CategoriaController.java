    package com.leandra.LivroService.controller;


    import com.leandra.LivroService.business.CategoriaService;
    import com.leandra.LivroService.business.dto.Request.CategoriaRequestDTO;
    import com.leandra.LivroService.business.dto.Response.CategoriaResponseDTO;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/categorias")
    @RequiredArgsConstructor
    public class CategoriaController {
        private final CategoriaService categoriaService;

        @PostMapping
        public ResponseEntity<CategoriaResponseDTO> salvarCategoria(@RequestBody CategoriaRequestDTO categoriaRequestDTO){
            return ResponseEntity.ok(categoriaService.salvaCategoria(categoriaRequestDTO));
        }
        @GetMapping
        public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias(){
            return ResponseEntity.ok(categoriaService.listarCategorias());
        }
        @GetMapping("/{id}")
        public ResponseEntity<CategoriaResponseDTO> buscaCategoriaPorId(@PathVariable Long id){
            return ResponseEntity.ok(categoriaService.buscaCategoriaPorId(id));
        }

        @PutMapping("/{id}")
        public ResponseEntity<CategoriaResponseDTO> atualizaCategoria(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaRequestDTO){
            return ResponseEntity.ok(categoriaService.atualizaCategoria(id,categoriaRequestDTO));
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletaCategoria(@PathVariable Long id){
            categoriaService.deletaCategoria(id);
            return ResponseEntity.noContent().build();
        }
    }
