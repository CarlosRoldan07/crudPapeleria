package com.papeleria.crud.repository;

import com.papeleria.crud.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio JPA para la entidad {@link Venta}.
 * Proporciona operaciones CRUD estándar y búsqueda personalizada por rango de fechas.
 */
public interface VentaRepository extends JpaRepository<Venta, Long> {

    /**
     * Busca todas las ventas realizadas entre dos fechas específicas.
     *
     * @param desde fecha de inicio (inclusive)
     * @param hasta fecha de fin (inclusive)
     * @return lista de ventas dentro del rango especificado
     */
    List<Venta> findByFechaBetween(LocalDateTime desde, LocalDateTime hasta);
}
