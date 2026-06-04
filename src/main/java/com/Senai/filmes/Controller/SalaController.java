package com.Senai.filmes.Controller;

import com.Senai.filmes.DTO.Response.FilmeResponse;
import com.Senai.filmes.DTO.Response.SalaResponse;
import com.Senai.filmes.DTO.Resquest.SalaRequest;
import com.Senai.filmes.Service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Tag(name = "Salas", description = "Endpoint para gerenciamento de salas do cinema.")
@RestController
@CrossOrigin("*")
@RequestMapping("/api/salas")
public class SalaController {
    @Autowired
    private SalaService salaService;

    @PostMapping

    @Operation(summary = "Cadastrar sala", description = "Rota para cadastrar sala")
    public ResponseEntity<SalaResponse> cadastrarSala(@RequestBody SalaRequest salarequest) {
        return new ResponseEntity<>(salaService.cadastrarSala(salarequest), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todos", description = "Rota para listar todas as salas")
    public ResponseEntity<List<SalaResponse>> listarTodos() {
        List<SalaResponse> salas = salaService.listarTodos();
        if (salas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(salas, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Listar sala por ID")
    public ResponseEntity<SalaResponse> buscarPorID(@PathVariable UUID id) {
        return new ResponseEntity<>(salaService.buscarPorId(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSala(@PathVariable UUID id){
       salaService.deletar(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }



}
