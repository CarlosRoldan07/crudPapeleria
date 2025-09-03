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

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<Venta> registrar(@RequestBody VentaRequest request) {
        return ResponseEntity.ok(
                ventaService.registrar(request.getProductoId(), request.getCantidad())
        );
    }
    @PostMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Funciona");
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listar() {
        return ResponseEntity.ok(ventaService.listar());
    }

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
