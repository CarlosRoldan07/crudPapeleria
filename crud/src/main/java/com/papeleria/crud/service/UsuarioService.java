package com.papeleria.crud.service;

import com.papeleria.crud.entity.Usuario;
import com.papeleria.crud.exception.AutenticacionException;
import com.papeleria.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de la gestión de usuarios.
 * Proporciona operaciones para registrar nuevos usuarios y autenticar credenciales.
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Codificador de contraseñas para el registro de nuevos usuarios.
     * Se utiliza BCrypt para asegurar las credenciales.
     */
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Codificador inyectado por Spring Security para validar contraseñas en login.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registra un nuevo usuario en la base de datos.
     * La contraseña se cifra antes de ser almacenada.
     *
     * @param usuario objeto {@link Usuario} con los datos a registrar
     * @return el usuario guardado con su ID asignado
     */
    public Usuario registrar(Usuario usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    /**
     * Autentica un usuario verificando sus credenciales.
     * Lanza una excepción si el usuario no existe o la contraseña es incorrecta.
     *
     * @param username nombre de usuario
     * @param password contraseña en texto plano
     * @return true si las credenciales son válidas
     * @throws AutenticacionException si el usuario no existe o la contraseña no coincide
     */
    public boolean autenticar(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new AutenticacionException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new AutenticacionException("Contraseña incorrecta");
        }

        return true;
    }
}
