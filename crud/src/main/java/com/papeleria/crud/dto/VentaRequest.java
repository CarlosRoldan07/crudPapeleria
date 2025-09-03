package com.papeleria.crud.dto;

import lombok.Data;

@Data
public class VentaRequest {
    private Long productoId;
    private Integer cantidad;
}
