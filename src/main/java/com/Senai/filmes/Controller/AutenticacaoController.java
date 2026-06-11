package com.Senai.filmes.Controller;

import com.Senai.filmes.DTO.Response.AuthResponse;
import com.Senai.filmes.DTO.Resquest.CadastroRequest;
import com.Senai.filmes.DTO.Resquest.LoginRequest;
import com.Senai.filmes.Model.Usuario;
import com.Senai.filmes.Service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
@Tag(name = "Autenticacao", description = "Endpoint para cadastro e login de usuarios")
public class AutenticacaoController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/cadastro")
    @Operation(summary = "cadastrar usuarios", description = "Cadastra um novo usuario e retorna o JWT token")
    public ResponseEntity<AuthResponse> cadastrar(@RequestBody CadastroRequest request){
        return new ResponseEntity<>(authenticationService.cadastrarUsuario(request), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    @Operation(summary = "login", description = "Autentica usuario e retorna um Token JWT")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return new ResponseEntity<>(authenticationService.login(request), HttpStatus.OK);
    }

}
