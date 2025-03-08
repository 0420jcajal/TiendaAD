package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.requests.ProductCreationRequest;


@Service
public class ProductService {

    private static final Logger logger= LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    public Product createProduct(ProductCreationRequest productCreationRequest){
        try {
            return productRepository.save(mapToProduct(productCreationRequest));
        } catch (Exception e) {
            logger.error("Error al crear el producto. Excepcion: {}", e);
            return null;
        }

            
    }
    public void removeProduct(Long id){
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error al borrar el producto con id {}. Excepcion: {}", id, e);
        }
        
    }
    public Optional<Product> getProducto(final Long id){
        try {
            return productRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error al recuperar el producto con id {}. Excepcion: {}", id, e);
            return null;
        }
        
    }
    public List<Product> getAllProduct(){
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            logger.error("Error al recuperar la lista de productos. Excepcion: {}", e);
            return null;
        }
        
    }
    public Product updateProduct(Long id, Product infoActualizar){
        try {
            Product product= productRepository.findById(id)
                    .orElseThrow(() -> {
                        RuntimeException exception = new RuntimeException("Producto no encontrado");
                        logger.error("Error al encontrar el producto con ID {}. Excepcion: {}", id, exception.getMessage(), exception);
                        return exception;
                    });
            product.setNombre(infoActualizar.getNombre());
            product.setDescripcion(infoActualizar.getDescripcion());
            product.setImagenPath(infoActualizar.getImagenPath());
            product.setStock(infoActualizar.getStock());
            product.setPrecio(infoActualizar.getPrecio());
            return productRepository.save(product);
        } catch (Exception e) {
            logger.error("Error al actualizar el producto con id {}. Excepcion: {}", id, e);
            return null;
        }
        
    }


    public Product mapToProduct(ProductCreationRequest productCreationRequest){
        Product product= new Product();
        product.setNombre(productCreationRequest.nombre());
        product.setDescripcion(productCreationRequest.descripcion());
        product.setImagenPath(productCreationRequest.imagenPath());
        product.setStock(productCreationRequest.stock());
        product.setPrecio(productCreationRequest.precio());
        return product;
    }
}
