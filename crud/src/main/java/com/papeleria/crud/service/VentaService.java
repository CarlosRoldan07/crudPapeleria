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

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Venta registrar(Long productoId, Integer cantidad) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));


        Venta venta = new Venta();
        venta.setProducto(producto);
        venta.setCantidad(cantidad);
        venta.setFecha(LocalDateTime.now());

        return ventaRepository.save(venta);
    }

    public List<Venta> listar() {
        return ventaRepository.findAll();
    }
    public List<Venta> buscarPorFecha(LocalDateTime desde, LocalDateTime hasta) {
        return ventaRepository.findByFechaBetween(desde, hasta);
    }

}
