package com.leandra.LivroService.business;

import com.leandra.LivroService.business.converter.CategoriaConverter;
import com.leandra.LivroService.business.dto.Request.CategoriaRequestDTO;
import com.leandra.LivroService.business.dto.Response.CategoriaResponseDTO;
import com.leandra.LivroService.infrastructure.entity.Categoria;
import com.leandra.LivroService.infrastructure.exceptions.ResouceNotFoundException;
import com.leandra.LivroService.infrastructure.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
        private final CategoriaRepository categoriaRepository;
        private final CategoriaConverter categoriaConverter;

    public CategoriaResponseDTO buscaCategoriaPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Erro ao buscar categoria por ID: " + id));
        return categoriaConverter.paraCategoriaResponseDTO(categoria);
    }
    public CategoriaResponseDTO salvaCategoria(CategoriaRequestDTO categoriaRequestDTO){
        try{
            if (categoriaRepository.existsByNome(categoriaRequestDTO.getNome())){
                throw new RuntimeException("Categoria já cadastrada");
            }
            Categoria categoria = categoriaConverter.paraCategoria(categoriaRequestDTO);
            return categoriaConverter.paraCategoriaResponseDTO(categoriaRepository.save(categoria));
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao salvar Categoria"+e.getMessage());
        }

    }

    public List<CategoriaResponseDTO> listarCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map(categoriaConverter::paraCategoriaResponseDTO).toList();
    }
    public void deletaCategoria(Long id){
        try {
            categoriaRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao deletar categoria"+e.getMessage());
        }
    }

    public CategoriaResponseDTO atualizaCategoria(Long id, CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Categoria não encontrada com ID: " + id));

        Categoria updatedCategoria = categoriaConverter.updateCategoria(categoriaRequestDTO, categoria);
        categoriaRepository.save(updatedCategoria);

        return categoriaConverter.paraCategoriaResponseDTO(updatedCategoria);
    }
}
