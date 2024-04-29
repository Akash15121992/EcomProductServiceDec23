package dev.sandeep.EcomProductServiceDec23.controller;

import dev.sandeep.EcomProductServiceDec23.dto.CreateProductRequestDTO;
import dev.sandeep.EcomProductServiceDec23.dto.ProductResponseDTO;
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
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        /*List<FakeStoreProductResponseDTO> products = productService.getAllProducts();*/
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable("id") UUID id){
        if(id == null){
            throw new InvalidInputException("Input is not correct as the id " +
                    "is negative and id can't be negative. Please re enter with correct id");
        }
        /*FakeStoreProductResponseDTO product = productService.getProduct(id);*/
        ProductResponseDTO product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody CreateProductRequestDTO product){
        //Product savedProduct = productService.createProduct(product);
        ProductResponseDTO savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    //cant update category and rating for a product
    @PutMapping("/{id}")
    public  ResponseEntity updateProduct(@PathVariable("id") UUID id, @RequestBody CreateProductRequestDTO productRequestDTO){
        return ResponseEntity.ok(
                productService.updateProduct(productRequestDTO,id));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Boolean> deleteProduct(@PathVariable("id") UUID id){
        return ResponseEntity.ok(
                productService.deleteProduct(id));
    }

    @GetMapping("/name/{productName}")
    public  ResponseEntity<ProductResponseDTO> getProductByProductName(@PathVariable("productName") String productName){
        return ResponseEntity.ok(
                productService.getProduct(productName));
    }

    //TODO : convert product list to product response dto
    @GetMapping("/{min}/{max}")
    public  ResponseEntity getProductsByPriceRange(@PathVariable("min") double minPrice,
                                                        @PathVariable("max") double maxPrice){
        return ResponseEntity.ok(
                productService.getProducts(minPrice, maxPrice));
    }
}

