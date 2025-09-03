package com.papeleria.crud.controller;

import com.papeleria.crud.entity.Producto;
import com.papeleria.crud.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de productos.
 * Expone endpoints para crear, actualizar, listar y eliminar productos.
 */
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Crea un nuevo producto.
     *
     * @param producto objeto {@link Producto} recibido en el cuerpo de la solicitud
     * @return el producto creado con su ID asignado
     */
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.crear(producto));
    }

    /**
     * Actualiza un producto existente por su ID.
     *
     * @param id       identificador del producto a modificar
     * @param producto objeto {@link Producto} con los nuevos datos
     * @return el producto actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }

    /**
     * Lista todos los productos registrados.
     *
     * @return lista de productos
     */
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listar());
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id identificador del producto a eliminar
     * @return respuesta sin contenido si la eliminación fue exitosa
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
