package com.Senai.filmes.Service;

import com.Senai.filmes.DTO.Response.FilmeResponse;
import com.Senai.filmes.DTO.Resquest.FilmeRequest;
import com.Senai.filmes.Model.Enums.GeneroFilme;
import com.Senai.filmes.Model.Filme;
import com.Senai.filmes.Repository.IFilmeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class FilmeService {
    @Autowired
    private IFilmeRepository filmeRepository;


    public List<FilmeResponse> listarTodos() {
        return filmeRepository.findAll().stream().map(this::toResponse).toList();
    }

    public FilmeResponse buscarPorId(UUID id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado"));
        return toResponse(filme);
    }

    public FilmeResponse cadastrarFilme(FilmeRequest request){
        Filme filme = new Filme();
        filme.setTitulo_filme(request.titulo());
        filme.setDescricao_filme(request.descricao());
        filme.setGenero(request.genero());
        filme.setDuracaoMinutos(request.duracaoMinutos());
        return toResponse(filmeRepository.save(filme));
    }


    private FilmeResponse toResponse(Filme filme) {
        return new FilmeResponse(filme.getId(), filme.getTitulo_filme(), filme.getDescricao_filme(), filme.getUrlPoster(), filme.getGenero(), filme.getDuracaoMinutos());
    }


}
