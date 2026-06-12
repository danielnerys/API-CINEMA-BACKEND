package com.Senai.filmes.Controller;

import com.Senai.filmes.DTO.Response.FilmeResponse;
import com.Senai.filmes.DTO.Resquest.FilmeRequest;
import com.Senai.filmes.Model.Filme;
import com.Senai.filmes.Service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/filmes")
@Tag(name = "Filmes", description = "Endpoint para gerenciar filmes.")
public class FilmeController {
    @Autowired
    private FilmeService filmeService;

    @GetMapping
    @Operation(summary = "Listar todos os filmes",description = "Rota para listar todos os filmes cadastrados.")
    public ResponseEntity<List<FilmeResponse>> listarTodos() {
        List<FilmeResponse> filmes = filmeService.listarTodos();

        if (filmes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(filmes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar filme por ID", description = "Lista todos os detalhes de um filme pelo seu ID")
    public ResponseEntity<FilmeResponse> buscarPorId(@PathVariable UUID id){
        return new ResponseEntity<>(filmeService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Cadastrar filme", description = "Rota para cadastrar um novo filme")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FilmeResponse> cadastrarFilme(@RequestBody FilmeRequest filme){
        return new ResponseEntity<>(filmeService.cadastrarFilme(filme), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar filme", description = "Atualiza os dados de um filme")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FilmeResponse> atualizarFilme(@PathVariable UUID id, @RequestBody FilmeRequest filmerequest){
        return new ResponseEntity<>(filmeService.atualizarFilme(id, filmerequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove filme", description = "Remover filme do sistema.")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FilmeResponse> deletarFilme(UUID id){
        filmeService.deletarFilme(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
