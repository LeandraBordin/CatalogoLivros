    package com.leandra.LivroService.controller;


    import com.leandra.LivroService.business.CategoriaService;
    import com.leandra.LivroService.business.dto.Request.CategoriaRequestDTO;
    import com.leandra.LivroService.business.dto.Response.CategoriaResponseDTO;
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
    @RequestMapping("/categorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Não Autorizado: Token ausente, inválido ou expirado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Proibido: O usuário não tem o cargo necessário", content = @Content)
    })
    @RequiredArgsConstructor
    @Tag(name = "Categoria", description = "Endpoints de gerenciamento de Categorias")
    public class CategoriaController {
        private final CategoriaService categoriaService;


        @PostMapping
        @Operation(summary = "Salva nova categoria", description = "Cadastra uma nova categoria. Retorna 409 se já existir uma categoria com o mesmo nome.")
        @ApiResponse(responseCode = "201", description = "Categoria salva com sucesso!")
        @ApiResponse(responseCode = "409", description = "Categoria já cadastrada")
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        public ResponseEntity<CategoriaResponseDTO> salvarCategoria(@RequestBody CategoriaRequestDTO categoriaRequestDTO){
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.salvaCategoria(categoriaRequestDTO));
        }

        @GetMapping
        @Operation(summary = "Lista categorias", description = "Retorna todas as categorias cadastradas. Retorna lista vazia se não houver nenhuma.")
        @ApiResponse(responseCode = "200", description = "Categorias listadas com sucesso!")
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias(){
            return ResponseEntity.ok(categoriaService.listarCategorias());
        }

        @GetMapping("/{id}")
        @Operation(summary = "Busca categoria por id", description = "Retorna a categoria correspondente ao id informado.")
        @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso!")
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        public ResponseEntity<CategoriaResponseDTO> buscaCategoriaPorId(@PathVariable Long id){
            return ResponseEntity.ok(categoriaService.buscaCategoriaPorId(id));
        }

        @PutMapping("/{id}")
        @Operation(summary = "Atualiza categoria", description = "Atualiza os dados da categoria identificada pelo id. Os campos enviados substituem os valores atuais.")
        @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso!")
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        public ResponseEntity<CategoriaResponseDTO> atualizaCategoria(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaRequestDTO){
            return ResponseEntity.ok(categoriaService.atualizaCategoria(id, categoriaRequestDTO));
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Deleta categoria por id", description = "Remove permanentemente a categoria correspondente ao id informado.")
        @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso!")
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        public ResponseEntity<Void> deletaCategoria(@PathVariable Long id){
            categoriaService.deletaCategoria(id);
            return ResponseEntity.noContent().build();
        }
    }
