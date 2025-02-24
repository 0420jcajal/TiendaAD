package com.example.demo.requests;

public record PedidoCreationRequest (long numeroPedido, String descripcion, float precio, String estado, String comprador){

}
