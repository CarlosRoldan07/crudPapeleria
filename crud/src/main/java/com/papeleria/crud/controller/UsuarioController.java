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

/**
 * Controlador REST para la gestión de usuarios.
 * Permite registrar nuevos usuarios y autenticar credenciales mediante JWT.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Registra un nuevo usuario en el sistema.
     * La contraseña se cifra antes de ser almacenada.
     *
     * @param usuario objeto {@link Usuario} recibido en el cuerpo de la solicitud
     * @return el usuario creado con su ID asignado
     */
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.registrar(usuario);
        return ResponseEntity.status(201).body(nuevo);
    }

    /**
     * Autentica un usuario y genera un token JWT si las credenciales son válidas.
     * Valida que el nombre de usuario y la contraseña no estén vacíos.
     *
     * @param request objeto {@link LoginRequest} con username y password
     * @return token JWT en caso de autenticación exitosa
     * @throws AutenticacionException si las credenciales son inválidas
     */
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
