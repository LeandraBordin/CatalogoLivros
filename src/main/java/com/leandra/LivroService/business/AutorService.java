package com.leandra.LivroService.business;

import com.leandra.LivroService.business.converter.AutorConverter;
import com.leandra.LivroService.business.dto.Request.AutorRequestDTO;
import com.leandra.LivroService.business.dto.Response.AutorResponseDTO;
import com.leandra.LivroService.infrastructure.entity.Autor;
import com.leandra.LivroService.infrastructure.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutorService {
    private final AutorRepository autorRepository;
    private final AutorConverter autorConverter;

    public AutorResponseDTO salvarAutor(AutorRequestDTO autorRequestDTO){
        try{
            if (autorRepository.existsByNome(autorRequestDTO.getNome())){
                throw new RuntimeException("Autor já cadastrado");
            }
            Autor autor = autorConverter.paraAutor(autorRequestDTO);
            return autorConverter.paraAutorResponseDTO(autorRepository.save(autor));
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao cadastrar autor"+e.getMessage());
        }
    }
}
