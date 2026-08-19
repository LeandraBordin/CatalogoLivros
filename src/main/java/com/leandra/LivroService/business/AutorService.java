package com.leandra.LivroService.business;

import com.leandra.LivroService.business.converter.AutorConverter;
import com.leandra.LivroService.business.dto.Request.AutorRequestDTO;
import com.leandra.LivroService.business.dto.Response.AutorResponseDTO;
import com.leandra.LivroService.infrastructure.entity.Autor;
import com.leandra.LivroService.infrastructure.repository.AutorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<AutorResponseDTO> buscaAutorPorNome(String nome){
        List<Autor> autores = autorRepository.findByNomeContainingIgnoreCase(nome);
        if (autores.isEmpty()) {
            throw new RuntimeException("Nenhum autor encontrado com o nome: " + nome);
        }
        return autores.stream().map(autorConverter::paraAutorResponseDTO).toList();
    }

    public List<AutorResponseDTO> listarAutores(){
        List<Autor> autores = autorRepository.findAll();
        return autores.stream().map(autorConverter::paraAutorResponseDTO).toList();
    }
    public AutorResponseDTO buscaAutorPorId(Long id){
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado para o ID: " + id));
        return autorConverter.paraAutorResponseDTO(autor);
    }
    public AutorResponseDTO atualizaAutor(Long id, AutorRequestDTO autorRequestDTO){
        Autor autorExistente = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado para o ID: " + id));
        autorExistente.setNome(autorRequestDTO.getNome());
        autorExistente.setNacionalidade(autorRequestDTO.getNacionalidade());
        Autor autorAtualizado = autorRepository.save(autorExistente);

        return autorConverter.paraAutorResponseDTO(autorAtualizado);
    }
    public void deletaAutor(Long id){
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado para o ID: " + id));

        if (!autor.getLivros().isEmpty()) {
            throw new IllegalStateException("Não é possível excluir autor vinculado a livros.");
        }

        autorRepository.delete(autor);
    }
}
