package com.Senai.filmes.Controller;

import com.Senai.filmes.DTO.Response.FilmeResponse;
import com.Senai.filmes.DTO.Response.SalaResponse;
import com.Senai.filmes.DTO.Resquest.SalaRequest;
import com.Senai.filmes.Service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/salas")
public class SalaController {
    @Autowired
    private SalaService salaService;

    @PostMapping
    @Operation(summary = "Cadastrar sala", description = "Rota para cadastrar sala")
    public ResponseEntity<SalaResponse> cadastrarSala(@RequestBody SalaRequest salarequest){
        return new ResponseEntity<>(salaService.cadastrarSala(salarequest), HttpStatus.CREATED);
    }



}
