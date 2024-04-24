package dev.sandeep.EcomProductServiceDec23.service;

import dev.sandeep.EcomProductServiceDec23.dto.FakeStoreProductResponseDTO;
import dev.sandeep.EcomProductServiceDec23.entity.Product;
import dev.sandeep.EcomProductServiceDec23.exception.ProductNotFoundException;
import dev.sandeep.EcomProductServiceDec23.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("productService")
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    //@Override
    //public List<FakeStoreProductResponseDTO> getAllProducts() {
    //    return null;
    //}
    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //@Override
    //public FakeStoreProductResponseDTO getProduct(UUID productId) throws ProductNotFoundException {
    //    return null;
    //}

    @Override
    public Product getProduct(UUID productId) throws ProductNotFoundException {
        /* basic code for null check
        Product savedProduct = productRepository.findById(productId).get();
        if(savedProduct == null){
            throw new ProductNotFoundException("Product not found for id : " + productId);
        }
        return savedProduct;
         */

        Product savedProduct = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id " + productId)
        );
        return savedProduct;
    }

    @Override
    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product updateProduct(Product updatedProduct, UUID productId) {
        Product savedProduct = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id " + productId)
        );
        savedProduct.setTitle(updatedProduct.getTitle());
        savedProduct.setPrice(updatedProduct.getPrice());
        savedProduct.setDescription(updatedProduct.getDescription());
        savedProduct.setCategory(updatedProduct.getCategory());
        savedProduct.setImageURL(updatedProduct.getImageURL());
        savedProduct.setRating(updatedProduct.getRating());
        savedProduct = productRepository.save(savedProduct);// save work as upsert ,which is insert and update
        return savedProduct;
    }

    @Override
    public boolean deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
        return true;
    }

    @Override
    public Product getProduct(String productName) {
        return productRepository.findProductByTitle(productName);
    }

    @Override
    public List<Product> getProducts(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
