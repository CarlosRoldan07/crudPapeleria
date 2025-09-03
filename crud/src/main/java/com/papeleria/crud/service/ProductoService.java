package com.papeleria.crud.service;

import com.papeleria.crud.entity.Producto;
import com.papeleria.crud.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de la gestión de productos en la papelería.
 * Proporciona operaciones CRUD básicas sobre la entidad {@link Producto}.
 */
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Registra un nuevo producto en la base de datos.
     *
     * @param producto objeto {@link Producto} con los datos a guardar
     * @return el producto guardado con su ID asignado
     */
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Actualiza los datos de un producto existente.
     *
     * @param id        identificador del producto a modificar
     * @param producto  objeto {@link Producto} con los nuevos datos
     * @return el producto actualizado
     */
    public Producto actualizar(Long id, Producto producto) {
        producto.setId(id);
        return productoRepository.save(producto);
    }

    /**
     * Obtiene la lista completa de productos registrados.
     *
     * @return lista de objetos {@link Producto}
     */
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id identificador del producto a eliminar
     */
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
