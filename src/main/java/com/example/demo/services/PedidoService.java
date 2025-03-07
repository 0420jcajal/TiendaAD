package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.models.Pedido;
import com.example.demo.models.Pedido_Producto;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.requests.PedidoCreationRequest;

import jakarta.transaction.Transactional;


@Service
public class PedidoService {

    private static final Logger logger= LoggerFactory.getLogger(PedidoService.class);

    private final PedidoRepository pedidoRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public PedidoService(PedidoRepository pedidoRepository, UserRepository userRepository, ProductRepository productRepository){
        this.pedidoRepository= pedidoRepository;
        this.userRepository= userRepository;
        this.productRepository= productRepository;
    }

    @Transactional
    public Pedido createPedido(PedidoCreationRequest pedidoCreationRequest){
        try {
            Pedido pedido= mapToPedido(pedidoCreationRequest);
            User user = userRepository.findById(pedidoCreationRequest.userId())
                    .orElseThrow(() -> {
                        RuntimeException exception = new RuntimeException("Usuario no encontrado");
                        logger.error("Error al encontrar el usuario con ID {}. Excepcion: {}", pedidoCreationRequest.userId(), exception.getMessage(), exception);
                        return exception;
                    });
            
            pedido.setUser(user);

            List<Pedido_Producto> pedidoProductos= pedidoCreationRequest.productos().stream().map(ProductoCantidad -> {
                Product producto = productRepository.findById(ProductoCantidad.productoId())
                    .orElseThrow(() -> {
                        RuntimeException exception = new RuntimeException("Producto no encontrado");
                        logger.error("Error al encontrar el producto con ID {}. Excepcion: {}", ProductoCantidad.productoId(), exception.getMessage(), exception);
                        return exception;
                    });
                Pedido_Producto pedidoProducto= new Pedido_Producto();
                pedidoProducto.setPedido(pedido);
                pedidoProducto.setProducto(producto);
                pedidoProducto.setCantidad(ProductoCantidad.cantidad());
                return pedidoProducto;
            }).toList();

            pedido.setPedidoProductos(pedidoProductos);
            pedido.setPrecio(pedido.calcularPrecioTotal());

            return pedidoRepository.save(pedido);
        } catch (Exception e) {
            logger.error("Error al crear el Pedido. Excepcion: {}", e);
            throw e; 
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
