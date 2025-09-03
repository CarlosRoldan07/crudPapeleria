package com.papeleria.crud.repository;

import com.papeleria.crud.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link Usuario}.
 * Proporciona operaciones CRUD estándar y búsqueda personalizada por nombre de usuario.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username nombre de usuario a buscar
     * @return un {@link Optional} que contiene el usuario si existe
     */
    Optional<Usuario> findByUsername(String username);
}
