package com.Senai.filmes.Service;

import com.Senai.filmes.DTO.Response.AuthResponse;
import com.Senai.filmes.DTO.Resquest.CadastroRequest;
import com.Senai.filmes.DTO.Resquest.LoginRequest;
import com.Senai.filmes.Model.Enums.CargoUsuario;
import com.Senai.filmes.Model.Usuario;
import com.Senai.filmes.Repository.IUsuarioRepository;
import com.Senai.filmes.Security.JwtUtil;

import com.Senai.filmes.Security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceIml;

//    criar cadastro do usuario

    public AuthResponse cadastrarUsuario(CadastroRequest request) {
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email ja esta cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuario.setCargo(CargoUsuario.USUARIO);

        usuarioRepository.save(usuario);

        UserDetails userDetails = userDetailsServiceIml.loadUserByUsername((request.email()));
        String token = jwtUtil.gerarToken(userDetails);

        return new AuthResponse(token, usuario.getNomeUsuario(), usuario.getCargo().name());

    }

    //login
    public AuthResponse login(LoginRequest loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.senha()
                )
        );

        Usuario usuario = usuarioRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UserDetails userDetails =
                userDetailsServiceIml.loadUserByUsername(loginRequest.email());

        String token = jwtUtil.gerarToken(userDetails);

        return new AuthResponse(
                token,
                usuario.getNomeUsuario(),
                usuario.getCargo().name()
        );
    }


}