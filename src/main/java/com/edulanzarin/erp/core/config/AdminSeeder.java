package com.edulanzarin.erp.core.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.edulanzarin.erp.comum.usuario.model.Usuario;
import com.edulanzarin.erp.comum.usuario.repository.UsuarioRepository;

@Configuration
public class AdminSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String emailAdmin = "admin@contiva.com";
        String senhaAdmin = "masterkey";

        Optional<Usuario> adminExistente = usuarioRepository.findByEmail(emailAdmin);

        if (adminExistente.isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNome("Administrador Contiva");
            admin.setEmail(emailAdmin);
            admin.setSenha(passwordEncoder.encode(senhaAdmin));
            usuarioRepository.save(admin);
        } else {
            Usuario admin = adminExistente.get();
            admin.setSenha(passwordEncoder.encode(senhaAdmin));
            usuarioRepository.save(admin);
        }
    }
}
