package com.example.demo.api;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Pedido;
import com.example.demo.services.PedidoService;
import com.example.demo.requests.PedidoCreationRequest;



@RestController
@RequestMapping("/api/v1/pedidos")
@CrossOrigin(origins="*")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService= pedidoService;
    }

    @PostMapping
    public Pedido createPedido(@RequestBody PedidoCreationRequest pedidoCreationRequest){
        return pedidoService.createPedido(pedidoCreationRequest);
    }
    @GetMapping("/{numeroPedido}")
    public Pedido getPedido(@PathVariable long numeroPedido){
        return pedidoService.getPedido(numeroPedido).orElse(null);
    }
    @DeleteMapping("/{numeroPedido}")
    public void deletePedido(@PathVariable long numeroPedido){
        pedidoService.removePedido(numeroPedido);
    }
    @GetMapping("/getall")
    public List<Pedido> getAllPedidos(){
        return pedidoService.getAllPedidos();
    }
}
