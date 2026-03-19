package com.aeroweb.sistemapasajes.config;

import com.aeroweb.sistemapasajes.entities.Usuario;
import com.aeroweb.sistemapasajes.entities.Rol;
import com.aeroweb.sistemapasajes.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository repository, PasswordEncoder encoder) {
        return args -> {
            if (repository.count() == 0) {

                Usuario admin = new Usuario();
                admin.setNombre("Admin");
                admin.setApellido("Principal");      // ✅ OBLIGATORIO
                admin.setEmail("admin@mail.com");
                admin.setDni("12345678");            // ✅ OBLIGATORIO
                admin.setPassword(encoder.encode("123456"));
                admin.setRol(Rol.ADMIN);
                admin.setActivo(true);               // ✅ recomendado

                repository.save(admin);

                System.out.println("✅ Admin creado: admin@mail.com / 123456");
            }
        };
    }
}