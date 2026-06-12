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
    public void run(String... args) {
        // 1. Tenta buscar o admin existente pelo e-mail
        // (Certifique-se de que seu IUsuarioRepository tem o método findByEmail)
        Usuario usuarioAdmin = usuarioRepository.findByEmail(adminEmail)
                .orElse(new Usuario());

        // 2. Se for um usuário novo (id é nulo), preenche os dados padrão
        if (usuarioAdmin.getId() == null) {
            usuarioAdmin.setNomeUsuario("DanielAdmin");
            usuarioAdmin.setEmail(adminEmail);
            usuarioAdmin.setCargo(CargoUsuario.ADMIN);
            System.out.println(">>>> USUÁRIO ADMIN NÃO ENCONTRADO. CRIANDO NOVO... <<<<");
        } else {
            System.out.println(">>>> ADMIN ENCONTRADO NO BANCO! FORÇANDO ATUALIZAÇÃO DA SENHA PARA BCRYPT... <<<<");
        }

        // 3. Criptografa a senha (isso vai sobrescrever a senha em texto puro antiga!)
        usuarioAdmin.setSenha(passwordEncoder.encode(adminSenha));

        // 4. Salva ou Atualiza no banco de dados
        usuarioRepository.save(usuarioAdmin);

        System.out.println(">>>>>>>> USUARIO ADMIN CONFIGURADO COM SUCESSO: " + adminEmail + " <<<<<<<<<<<");
    }



}
