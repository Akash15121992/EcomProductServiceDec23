package dev.sandeep.EcomProductServiceDec23.service;

import dev.sandeep.EcomProductServiceDec23.dto.CreateProductRequestDTO;
import dev.sandeep.EcomProductServiceDec23.dto.ProductResponseDTO;
import dev.sandeep.EcomProductServiceDec23.entity.Category;
import dev.sandeep.EcomProductServiceDec23.entity.Product;
import dev.sandeep.EcomProductServiceDec23.exception.CategoryNotFoundException;
import dev.sandeep.EcomProductServiceDec23.exception.ProductNotFoundException;
import dev.sandeep.EcomProductServiceDec23.mapper.ProductEntityDTOMapper;
import dev.sandeep.EcomProductServiceDec23.repository.CategoryRepository;
import dev.sandeep.EcomProductServiceDec23.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("productService")
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductResponseDTO> getAllProducts(){

        List<Product> savedProduct = productRepository.findAll();// repo returns entity , will have to convert
        //entity to responseDTO by making call to Productentitydtomapper convertProductEntityToProductResponseDTO()
        //to return back to controller
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for(Product product:savedProduct){
            productResponseDTOS.add(ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product));
        }
        return  productResponseDTOS;
    }

    @Override
    public ProductResponseDTO getProduct(UUID productId) throws ProductNotFoundException {
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
        // repo returns entity , will have to convert
        //entity to responseDTO by making call to Productentitydtomapper convertProductEntityToProductResponseDTO()
        //to return back to controller
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO createProduct(CreateProductRequestDTO productRequestDTO) {
        // we are getting createproductreq dto object from controller , will have to convert the
        // dto to entity here by making call to ,mapper class and writing method there for dto to entity conversion
        //Product savedProduct = productRepository.save(product);
        //return savedProduct;

        Product product = ProductEntityDTOMapper.
                convertCreateProductRequestDTOToProduct(productRequestDTO);
        Category savedCategory = categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow(
                () -> new CategoryNotFoundException("Category not found for id " +
                        productRequestDTO.getCategoryId())
        );

        product.setCategory(savedCategory);
        product = productRepository.save(product);

        List<Product> categoryProducts = savedCategory.getProducts();
        categoryProducts.add(product);
        savedCategory.setProducts(categoryProducts);
        categoryRepository.save(savedCategory);
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product);
    }

    @Override
    public ProductResponseDTO updateProduct(CreateProductRequestDTO createProductRequestDTO,
                                            UUID productId) {
        Product savedProduct = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id " + productId)
        );
        savedProduct.setTitle(createProductRequestDTO.getTitle());
        savedProduct.setPrice(createProductRequestDTO.getPrice());
        savedProduct.setDescription(createProductRequestDTO.getDescription());
        savedProduct.setImageURL(createProductRequestDTO.getImageURL());
        savedProduct = productRepository.save(savedProduct);// save work as upsert ,which is insert and update
        return ProductEntityDTOMapper.
                convertProductEntityToProductResponseDTO(savedProduct);
    }

    @Override
    public boolean deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
        return true;
    }

    @Override
    public ProductResponseDTO getProduct(String productName) {
        //return productRepository.findProductByTitle(productName);
        return ProductEntityDTOMapper.
                convertProductEntityToProductResponseDTO(productRepository.findProductByTitle(productName));
    }

    @Override
    public List<Product> getProducts(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
        //return  ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(productRepository.findByPriceBetween(minPrice, maxPrice));
    }
}
