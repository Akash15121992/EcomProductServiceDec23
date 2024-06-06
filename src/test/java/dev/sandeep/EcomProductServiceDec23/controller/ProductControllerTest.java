package dev.sandeep.EcomProductServiceDec23.controller;

import dev.sandeep.EcomProductServiceDec23.dto.CreateProductRequestDTO;
import dev.sandeep.EcomProductServiceDec23.dto.ProductResponseDTO;
import dev.sandeep.EcomProductServiceDec23.entity.Product;
import dev.sandeep.EcomProductServiceDec23.exception.CategoryNotFoundException;
import dev.sandeep.EcomProductServiceDec23.exception.InvalidInputException;
import dev.sandeep.EcomProductServiceDec23.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateProductSuccess(){
        //Arrange
        CreateProductRequestDTO createProductRequestDTO = new CreateProductRequestDTO();
        createProductRequestDTO.setTitle("NewProductName");

        UUID productId = UUID.randomUUID();
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setProductId(productId);
        productResponseDTO.setTitle("NewProductName");

        Mockito.when(productService.updateProduct(createProductRequestDTO, productId)).
                thenReturn(productResponseDTO);
        //Act

        ResponseEntity<ProductResponseDTO> productResponseDTOResponseEntity = productController
                .updateProduct(productId, createProductRequestDTO);

        //Assert
        Assertions.assertEquals(productResponseDTO,productResponseDTOResponseEntity.getBody());
    }

    @Test
    public void testCreateProduct(){
        //Arrange
        CreateProductRequestDTO createProductRequestDTO = new CreateProductRequestDTO();
        createProductRequestDTO.setTitle("Macbook");

        UUID productId = UUID.randomUUID();
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setTitle("Macbook");
        productResponseDTO.setProductId(productId);

        Mockito.when(productService.createProduct(createProductRequestDTO))
                .thenReturn(productResponseDTO);
        //Act
        ResponseEntity<ProductResponseDTO> productResponseDTOResponseEntity = productController
                .createProduct(createProductRequestDTO);

        //Assert
        Assertions.assertEquals(productResponseDTO,productResponseDTOResponseEntity.getBody());
    }

    @Test
    public void testGetProductByProductName(){
        //Arrange
        String productName = "Macbook";

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setTitle("Macbook");

        Mockito.when(productService.getProduct(productName)).thenReturn(productResponseDTO);

        //Act
        ResponseEntity<ProductResponseDTO> productResponseDTOResponseEntity = productController
                .getProductByProductName(productName);

        //Assert
        Assertions.assertEquals(productResponseDTO,productResponseDTOResponseEntity.getBody());
    }

    @Test
    public void testGetProductsByPriceRange(){
        //Arrange
        double minPrice = 10.00;
        double maxPrice = 100.00;
        List<Product> mockProducts = new ArrayList<>();

        UUID productId = UUID.randomUUID();
        Product product1 = new Product();
        product1.setId(productId);
        product1.setTitle("Macbook");
        product1.setPrice(100.00);

        Product product2 = new Product();
        product2.setId(productId);
        product2.setTitle("Mobile");
        product2.setPrice(10.00);

        mockProducts.add(product1);
        mockProducts.add(product2);

        Mockito.when(productService.getProducts(minPrice,maxPrice)).thenReturn(mockProducts);

        //Act

        ResponseEntity productsByPriceRange =
                productController.getProductsByPriceRange(minPrice, maxPrice);

        //Assert
        Assertions.assertEquals(mockProducts,productsByPriceRange.getBody());
    }

    @Test
    public void testDeleteProduct(){
        //Arrange
        UUID productId = UUID.randomUUID();
        Boolean status = true;
        Mockito.when(productService.deleteProduct(productId)).thenReturn(status);

        //Act
        ResponseEntity<Boolean> booleanResponseEntity = productController.deleteProduct(productId);

        //Assert
        Assertions.assertEquals(status,booleanResponseEntity.getBody());
    }

    @Test
    public void testGetProductById(){
        //Arrange
        UUID productId = UUID.randomUUID();
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setProductId(productId);
        productResponseDTO.setTitle("Macbook");

        Mockito.when(productService.getProduct(productId)).thenReturn(productResponseDTO);
        //Act

        //ResponseEntity<ProductResponseDTO> productResponseDTOResponseEntity =
        ProductResponseDTO productById = productController.getProductById(productId);
        //Assert
        //Assertions.assertEquals(productResponseDTO,productResponseDTOResponseEntity.getBody());
        Assertions.assertEquals(productResponseDTO,productById);
    }

    @Test
    public void testGetAllProducts(){
        //Arrange
        List<ProductResponseDTO> mockProducts = new ArrayList<>();

        UUID productId = UUID.randomUUID();
        ProductResponseDTO product1 = new ProductResponseDTO();
        product1.setProductId(productId);
        product1.setTitle("Macbook");

        ProductResponseDTO product2 = new ProductResponseDTO();
        product2.setProductId(productId);
        product2.setTitle("Mobile");

        mockProducts.add(product1);
        mockProducts.add(product2);

        Mockito.when(productService.getAllProducts()).thenReturn(mockProducts);

        //Act
        ResponseEntity<List<ProductResponseDTO>> allProducts = productController.getAllProducts();

        //Assert
        Assertions.assertEquals(mockProducts,allProducts.getBody());
    }

    @Test
    public void testInvalidInputExceptionThrown(){
        //Arrange
        UUID nullId = null;

        InvalidInputException exception = Assertions.assertThrows(InvalidInputException.class, () -> {
            productController.getProductById(nullId);
        });

        //Assertions.assertEquals("Input is not correct as the id is negative and id can't be negative. Please re-enter with correct id", exception.getMessage());
    }
}
