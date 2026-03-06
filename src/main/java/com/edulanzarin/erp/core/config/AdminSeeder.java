package com.edulanzarin.erp.core.config;

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
        // só cria o admin se a tabela de usuários estiver totalmente vazia
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setNome("Administrador Contiva");
            admin.setEmail("admin@contiva.com");
            admin.setSenha(passwordEncoder.encode("123456"));

            usuarioRepository.save(admin);
            System.out.println("Usuário admin@contiva.com criado com senha 123456");
        }
    }
}
