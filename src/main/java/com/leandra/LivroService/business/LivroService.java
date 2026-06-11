package com.leandra.LivroService.business;

import com.leandra.LivroService.business.converter.LivroConverter;
import com.leandra.LivroService.business.dto.LivroDTO;
import com.leandra.LivroService.business.dto.Request.LivroRequestDTO;
import com.leandra.LivroService.business.dto.Response.LivroResponseDTO;
import com.leandra.LivroService.infrastructure.entity.Autor;
import com.leandra.LivroService.infrastructure.entity.Categoria;
import com.leandra.LivroService.infrastructure.entity.Livro;
import com.leandra.LivroService.infrastructure.repository.AutorRepository;
import com.leandra.LivroService.infrastructure.repository.CategoriaRepository;
import com.leandra.LivroService.infrastructure.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroConverter livroConverter;
    private final CategoriaRepository categoriaRepository;
    private final AutorRepository autorRepository;
    public LivroResponseDTO salvaLivro(LivroRequestDTO livroDTO){
        try{
            if (livroRepository.existsByIsbn(livroDTO.getIsbn())){
                throw new RuntimeException("Livro com ISBN"+livroDTO.getIsbn()+"já cadastrado");
            }
            List<Categoria> categorias =
                    categoriaRepository.findAllById(
                            livroDTO.getCategoriasIds());

            List<Autor> autores =
                    autorRepository.findAllById(
                            livroDTO.getAutoresIds());
            Livro livro = livroConverter.paraLivro(livroDTO);
            livro.setAutores(autores);
            livro.setCategorias(categorias);
            return livroConverter.paraLivroDTO(livroRepository.save(livro));
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao salvar livro:"+e.getMessage());
        }
    }

    public LivroResponseDTO buscaLivroPorIsbn(String isbn){
        try{
           return livroRepository.findByIsbn(isbn).map(livroConverter::paraLivroDTO)
                   .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado para o ISBN"+isbn));
        } catch (RuntimeException e) {
            throw new RuntimeException("Livro não encontrado"+e.getCause());
        }
    }

    public List<LivroResponseDTO> buscaLivroPorAutor(String nomeAutor){
        try{
            Autor autor = autorRepository.findByNome(nomeAutor).orElseThrow(() ->
                new EntityNotFoundException("Livro não encontrado para o autor"+nomeAutor));
            return livroRepository.findByAutoresId(autor.getId()).stream().map(livroConverter::paraLivroDTO).toList();
        } catch (RuntimeException e) {
            throw new RuntimeException("Livro não encontrado"+e.getCause());
        }
    }

    public List<LivroResponseDTO> buscaLivroPorCategoria(String categoria){
        try {
            Categoria categoriaFilme = categoriaRepository.findByNome(categoria)
                    .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado para a categoria"+categoria));
            return livroRepository.findByCategoriasId(categoriaFilme.getId()).stream().map(livroConverter::paraLivroDTO).toList();
        }catch (RuntimeException e) {
            throw new RuntimeException("Livro não encontrado"+e.getCause());
        }
    }
}
