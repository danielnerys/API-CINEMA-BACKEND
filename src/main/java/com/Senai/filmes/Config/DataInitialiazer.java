package com.Senai.filmes.Config;

import com.Senai.filmes.Model.Enums.CargoUsuario;
import com.Senai.filmes.Model.Usuario;
import com.Senai.filmes.Repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component

public class DataInitialiazer implements CommandLineRunner {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${admin.email:admin@email.com}")
    private String adminEmail;


    @Value("${admin.senha:Admin@123}")
    private String adminSenha;


    @Override
    public void run(String... args){
        if(usuarioRepository.existsByEmail(adminEmail)){
            return;
        }
        Usuario usuarioAdmin = new Usuario();
        usuarioAdmin.setNomeUsuario("DanielAdmin");
        usuarioAdmin.setEmail(adminEmail);
        usuarioAdmin.setSenha(passwordEncoder.encode(adminSenha));
        usuarioAdmin.setCargo(CargoUsuario.ADMIN);

        usuarioRepository.save(usuarioAdmin);

        System.out.println(">>>>>>>> USUARIO ADMIN CRIADO: " + adminEmail + " <<<<<<<<<<<");
    }



}
