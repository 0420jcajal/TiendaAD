package com.example.demo.api;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Product;
import com.example.demo.requests.ProductCreationRequest;
import com.example.demo.services.ProductService;


@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductCreationRequest productCreationRequest){
        return productService.createProduct(productCreationRequest);
    }
     @GetMapping("/{id}")
    public Product getProduct(@PathVariable long id){
        return productService.getProducto(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id){
        productService.removeProduct(null);
    }

    @GetMapping("/getall")
    public List<Product> getAllProducts(){
        return productService.getAllProduct();
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

}
