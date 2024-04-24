package dev.sandeep.EcomProductServiceDec23.service;

import dev.sandeep.EcomProductServiceDec23.dto.FakeStoreProductResponseDTO;
import dev.sandeep.EcomProductServiceDec23.entity.Product;
import dev.sandeep.EcomProductServiceDec23.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    // crud operation
    //List<FakeStoreProductResponseDTO> getAllProducts();
    List<Product> getAllProducts();
    //FakeStoreProductResponseDTO getProduct(int productId) throws ProductNotFoundException;
    Product getProduct(UUID productId) throws ProductNotFoundException;
    Product createProduct(Product product);
    Product updateProduct(Product updatedProduct, UUID productId);
    boolean deleteProduct(UUID productId);
    Product getProduct(String productName);
    List<Product> getProducts(double minPrice,double maxPrice);
}
