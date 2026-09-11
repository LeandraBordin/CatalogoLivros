package com.leandra.LivroService.business;

import com.leandra.LivroService.business.converter.LivroConverter;
import com.leandra.LivroService.business.dto.Request.LivroRequestDTO;
import com.leandra.LivroService.business.dto.Response.LivroResponseDTO;
import com.leandra.LivroService.infrastructure.entity.Autor;
import com.leandra.LivroService.infrastructure.entity.Categoria;
import com.leandra.LivroService.infrastructure.entity.Livro;
import com.leandra.LivroService.infrastructure.exceptions.ResouceNotFoundException;
import com.leandra.LivroService.infrastructure.repository.AutorRepository;
import com.leandra.LivroService.infrastructure.repository.CategoriaRepository;
import com.leandra.LivroService.infrastructure.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
    public List<LivroResponseDTO> listarLivros(){
        List<Livro> livros = livroRepository.findAll();
        return livros.stream().map(livroConverter::paraLivroDTO).toList();
    }
    public LivroResponseDTO buscaLivroPorIsbn(String isbn){
        try{
           return livroRepository.findByIsbn(isbn).map(livroConverter::paraLivroDTO)
                   .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado para o ISBN"+isbn));
        } catch (RuntimeException e) {
            throw new RuntimeException("Livro não encontrado"+e.getCause());
        }
    }
    public List<LivroResponseDTO> buscaLivrosComFiltros(String nome, String autor, String categoria){
        return livroRepository.buscaLivrosComFiltros(nome,autor,categoria).stream().map(livroConverter::paraLivroDTO).toList();
    }
    public LivroResponseDTO atualizarLivro(String isbn, LivroRequestDTO livroDTO) {
        Livro livro = livroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado com ISBN: " + isbn));

        if (livroDTO.getIsbn() != null && !livro.getIsbn().equals(livroDTO.getIsbn())) {
            if (livroRepository.existsByIsbn(livroDTO.getIsbn())) {
                throw new RuntimeException("Livro com ISBN " + livroDTO.getIsbn() + " já cadastrado");
            }
        }
        livro = livroConverter.updateLivro(livroDTO, livro);

        if (livroDTO.getCategoriasIds() != null) {
            List<Categoria> categorias = categoriaRepository.findAllById(livroDTO.getCategoriasIds());
            livro.setCategorias(categorias);
        }
        if (livroDTO.getAutoresIds() != null) {
            List<Autor> autores = autorRepository.findAllById(livroDTO.getAutoresIds());
            livro.setAutores(autores);
        }

        if (livroDTO.getQuantidadeTotal() != null) {
            livro.setQuantidadeTotal(livroDTO.getQuantidadeTotal());
        }

        Livro livroAtualizado = livroRepository.save(livro);
        return livroConverter.paraLivroDTO(livroAtualizado);
    }
    public void deletaLivroPorId(Long id){
       try{
           livroRepository.deleteById(id);
       } catch (ResouceNotFoundException e){
           throw new ResouceNotFoundException("Erro ao deletar livro: "+e.getMessage()+" ID: "+id+"");
       }
    }
    @Transactional
    public void deletaLivroPorIsbn(String isbn){
       try{
           livroRepository.deleteByIsbn(isbn);
       } catch (ResouceNotFoundException e){
           throw new ResouceNotFoundException("Erro ao deletar livro: "+e.getMessage()+" ID: "+isbn+"");
       }
    }
}
