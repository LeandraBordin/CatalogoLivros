package com.leandra.LivroService.business;

import com.leandra.LivroService.business.converter.CategoriaConverter;
import com.leandra.LivroService.business.dto.Request.CategoriaRequestDTO;
import com.leandra.LivroService.business.dto.Response.CategoriaResponseDTO;
import com.leandra.LivroService.infrastructure.entity.Categoria;
import com.leandra.LivroService.infrastructure.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {
        private final CategoriaRepository categoriaRepository;
        private final CategoriaConverter categoriaConverter;

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
}
