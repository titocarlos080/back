package com.example.back.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.entity.Usuario;
import com.example.back.repository.UsuarioRepository;

@RestController

public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private   BCryptPasswordEncoder passwordEncoder;

    // Login - Authenticate user
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
 
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(authRequest.getUsername());    
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (passwordEncoder.matches(authRequest.getPassword(), usuario.getPassword())) {
                // Successful login, return a token or a success message
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    // Register - Create a new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) {
        if (usuarioRepository.existsById(authRequest.getUsername())) {
            return ResponseEntity.status(400).body("User already exists");
        }

        Usuario newUsuario = new Usuario();
        newUsuario.setUser(authRequest.getUsername());
        newUsuario.setPassword(passwordEncoder.encode(authRequest.getPassword())); // Encrypt the password
        newUsuario.setNombre(authRequest.getNombre());
        newUsuario.setApellidos(authRequest.getApellidos());
        newUsuario.setSexo(authRequest.getSexo());
        newUsuario.setFnac(authRequest.getFnac());
        newUsuario.setTelefono(authRequest.getTelefono());
        newUsuario.setCorreo(authRequest.getCorreo());
        newUsuario.setRol(authRequest.getRol());
        newUsuario.setFotoPath(authRequest.getFotoPath());
        newUsuario.setEspecialidad(authRequest.getEspecialidad());
        newUsuario.setToken(authRequest.getToken());

        usuarioRepository.save(newUsuario);
        return ResponseEntity.status(201).body("User registered successfully");
    }

    // Logout - Invalidate token or session (Depends on your authentication method)
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody AuthRequest authRequest) {
        // If you're using JWT, you might simply delete the token or set it as invalid on the client side.
        // Here we assume a simple token invalidation method or session management.
        
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(authRequest.getUsername());
        
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setToken(null); // Clear the token
            usuarioRepository.save(usuario);
            return ResponseEntity.ok("Logged out successfully");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }
}
