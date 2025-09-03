package com.papeleria.crud.repository;

import com.papeleria.crud.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad {@link Producto}.
 * Proporciona operaciones CRUD estándar sobre productos,
 * incluyendo persistencia, búsqueda y eliminación.
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {}
