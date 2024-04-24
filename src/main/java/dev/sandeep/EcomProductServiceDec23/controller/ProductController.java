package dev.sandeep.EcomProductServiceDec23.controller;

import dev.sandeep.EcomProductServiceDec23.dto.FakeStoreProductResponseDTO;
import dev.sandeep.EcomProductServiceDec23.entity.Product;
import dev.sandeep.EcomProductServiceDec23.exception.InvalidInputException;
import dev.sandeep.EcomProductServiceDec23.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService; // field injection

    @GetMapping
    public ResponseEntity getAllProducts(){
        /*List<FakeStoreProductResponseDTO> products = productService.getAllProducts();*/
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);

    }

    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable("id") UUID id){
        if(id == null){
            throw new InvalidInputException("Input is not correct as the id " +
                    "is negative and id can't be negative. Please re enter with correct id");
        }
        /*FakeStoreProductResponseDTO product = productService.getProduct(id);*/
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody Product product){
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    public  ResponseEntity updateProduct(@PathVariable("id") UUID id, @RequestBody Product product){
        return ResponseEntity.ok(
                productService.updateProduct(product,id));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity deleteProduct(@PathVariable("id") UUID id){
        return ResponseEntity.ok(
                productService.deleteProduct(id));
    }

    @GetMapping("/name/{productName}")
    public  ResponseEntity getProductByProductName(@PathVariable("productName") String productName){
        return ResponseEntity.ok(
                productService.getProduct(productName));
    }

    @GetMapping("/{min}/{max}")
    public  ResponseEntity getProductsByPriceRange(@PathVariable("min") double minPrice,
                                                        @PathVariable("max") double maxPrice){
        return ResponseEntity.ok(
                productService.getProducts(minPrice, maxPrice));
    }
}

