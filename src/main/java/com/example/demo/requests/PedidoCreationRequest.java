package com.example.demo.requests;

import java.util.List;


public record PedidoCreationRequest (long numeroPedido, String descripcion, float precio, String estado, String comprador,Long userId, List<ProductoCantidad> productos) {
    
    public static record ProductoCantidad(Long productoId, int cantidad) {
    }
}
