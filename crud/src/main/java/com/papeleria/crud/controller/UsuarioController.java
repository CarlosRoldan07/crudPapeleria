package com.papeleria.crud.controller;

import com.papeleria.crud.dto.LoginRequest;
import com.papeleria.crud.entity.Usuario;
import com.papeleria.crud.exception.AutenticacionException;
import com.papeleria.crud.security.JwtUtil;
import com.papeleria.crud.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.registrar(usuario);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            if (request.getUsername() == null || request.getUsername().isBlank()) {
                throw new AutenticacionException("El nombre de usuario está vacío");
            }

            if (request.getPassword() == null || request.getPassword().isBlank()) {
                throw new AutenticacionException("La contraseña está vacía");
            }

            usuarioService.autenticar(request.getUsername(), request.getPassword());
            String token = jwtUtil.generarToken(request.getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (AutenticacionException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado en el login");
        }
    }

}

