package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.models.Pedido;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.requests.PedidoCreationRequest;

@Service
public class PedidoService {

    private static final Logger logger= LoggerFactory.getLogger(PedidoService.class);

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository){
        this.pedidoRepository= pedidoRepository;
    }

    public Pedido createPedido(PedidoCreationRequest pedidoCreationRequest){
        try {
            return pedidoRepository.save(mapToPedido(pedidoCreationRequest));
        } catch (Exception e) {
            logger.error("Error al crear el Pedido. Excepcion: {}", e);
            return null; 
        }
        
    }
    public void removePedido(Long numeroPedido){
        try {
            pedidoRepository.deleteById(numeroPedido);
        } catch (Exception e) {
            logger.error("Error al borrar el pedido con numero {}. Excepcion: {}",numeroPedido, e); 
        }
        pedidoRepository.deleteById(numeroPedido);
    }
    public Optional<Pedido> getPedido(final Long numeroPedido){
        try {
            return pedidoRepository.findById(numeroPedido);
        } catch (Exception e) {
            logger.error("Error al recuperar el pedido con numero {}. Excepcion: {}",numeroPedido, e);
            return null;
        }
    }
    public List<Pedido> getAllPedidos(){
        try {
            return pedidoRepository.findAll();
        } catch (Exception e) {
            logger.error("Error al recuperar la lista de pedidos. Excepcion: {}", e);
            return null;
        }

    }
    

    public Pedido mapToPedido(PedidoCreationRequest pedidoCreationRequest){
        Pedido pedido= new Pedido();
        pedido.setNumeroPedido(pedidoCreationRequest.numeroPedido());
        pedido.setDescripcion(pedidoCreationRequest.descripcion());
        pedido.setPrecio(pedidoCreationRequest.precio());
        pedido.setEstado(pedidoCreationRequest.estado());
        pedido.setComprador(pedidoCreationRequest.comprador());
        return pedido;
    }
}
