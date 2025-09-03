package com.papeleria.crud.service;

import com.papeleria.crud.entity.Producto;
import com.papeleria.crud.entity.Venta;
import com.papeleria.crud.repository.ProductoRepository;
import com.papeleria.crud.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio encargado de gestionar las ventas realizadas en la papelería.
 * Permite registrar nuevas ventas y consultar el historial por fecha.
 */
@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Registra una nueva venta asociada a un producto existente.
     * Si el producto no existe, lanza una excepción con estado 404.
     *
     * @param productoId ID del producto vendido
     * @param cantidad   cantidad vendida
     * @return la venta registrada con fecha y producto asociado
     * @throws ResponseStatusException si el producto no se encuentra
     */
    public Venta registrar(Long productoId, Integer cantidad) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        Venta venta = new Venta();
        venta.setProducto(producto);
        venta.setCantidad(cantidad);
        venta.setFecha(LocalDateTime.now());

        return ventaRepository.save(venta);
    }

    /**
     * Obtiene la lista completa de ventas registradas.
     *
     * @return lista de objetos {@link Venta}
     */
    public List<Venta> listar() {
        return ventaRepository.findAll();
    }

    /**
     * Busca ventas realizadas dentro de un rango de fechas.
     *
     * @param desde fecha de inicio (inclusive)
     * @param hasta fecha de fin (inclusive)
     * @return lista de ventas dentro del rango especificado
     */
    public List<Venta> buscarPorFecha(LocalDateTime desde, LocalDateTime hasta) {
        return ventaRepository.findByFechaBetween(desde, hasta);
    }
}
