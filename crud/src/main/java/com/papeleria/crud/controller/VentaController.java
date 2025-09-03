package com.papeleria.crud.controller;

import com.papeleria.crud.dto.VentaRequest;
import com.papeleria.crud.entity.Venta;
import com.papeleria.crud.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Controlador REST para la gestión de ventas.
 * Permite registrar nuevas ventas, listar todas y consultar el histórico por rango de fechas.
 */
@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    /**
     * Registra una nueva venta a partir de los datos recibidos.
     *
     * @param request objeto {@link VentaRequest} con ID de producto y cantidad
     * @return la venta registrada con fecha y producto asociado
     */
    @PostMapping
    public ResponseEntity<Venta> registrar(@RequestBody VentaRequest request) {
        return ResponseEntity.ok(
                ventaService.registrar(request.getProductoId(), request.getCantidad())
        );
    }

    /**
     * Endpoint de prueba para verificar funcionamiento del controlador.
     *
     * @return mensaje de confirmación
     */
    @PostMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Funciona");
    }

    /**
     * Lista todas las ventas registradas en el sistema.
     *
     * @return lista de objetos {@link Venta}
     */
    @GetMapping
    public ResponseEntity<List<Venta>> listar() {
        return ResponseEntity.ok(ventaService.listar());
    }

    /**
     * Consulta el histórico de ventas dentro de un rango de fechas.
     * Las fechas deben estar en formato ISO: yyyy-MM-dd'T'HH:mm:ss
     *
     * @param desde fecha de inicio (inclusive)
     * @param hasta fecha de fin (inclusive)
     * @return lista de ventas dentro del rango o mensaje de error si el formato es inválido
     */
    @GetMapping("/historico")
    public ResponseEntity<?> buscarPorFecha(
            @RequestParam String desde,
            @RequestParam String hasta
    ) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime desdeDate = LocalDateTime.parse(desde, formatter);
            LocalDateTime hastaDate = LocalDateTime.parse(hasta, formatter);

            List<Venta> ventas = ventaService.buscarPorFecha(desdeDate, hastaDate);
            return ResponseEntity.ok(ventas);
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Formato de fecha inválido. Usa yyyy-MM-dd'T'HH:mm:ss");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al consultar el histórico de ventas");
        }
    }
}
