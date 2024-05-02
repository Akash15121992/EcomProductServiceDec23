package dev.sandeep.EcomProductServiceDec23.service;

import dev.sandeep.EcomProductServiceDec23.entity.Category;
import dev.sandeep.EcomProductServiceDec23.entity.Product;
import dev.sandeep.EcomProductServiceDec23.exception.CategoryNotFoundException;
import dev.sandeep.EcomProductServiceDec23.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CategoryServiceImplTest {

    @Mock // mock for all the dependencies
    private CategoryRepository categoryRepository;

    @InjectMocks // @InjectMocks is for the actual class we are testing
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);//this method is not used now , It initializes and adds all mocks in the test class

    }
    //this method will return total cost of all products under a category
    @Test
    public void testGetTotalPriceForMultipleProductUnderCategory(){
        //Arrange
        UUID categoryId = UUID.randomUUID();
        Optional<Category> categoryOptionalMockData = getCategoryData();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(categoryOptionalMockData);
        double expectedTotalCost = 300.00;

        //Act
        double actualTotalCost = categoryService.getTotalPriceForCategory(categoryId);

        //Assert
        Assertions.assertEquals(actualTotalCost,expectedTotalCost);
    }

    //mock data
    private Optional<Category> getCategoryData(){
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("CategoryName");

        Product product1 = new Product();
        product1.setId(UUID.randomUUID());
        product1.setTitle("Product1");
        product1.setPrice(100.00);
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(UUID.randomUUID());
        product2.setTitle("Product2");
        product2.setPrice(200.00);
        product2.setCategory(category);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        category.setProducts(products);
        return  Optional.of(category);
    }

    @Test
    public void testGetTotalPriceForZeroProductUnderCategory(){
        //this method will return total cost of all products under a category
        //Arrange
        UUID categoryId = UUID.randomUUID();
        Optional<Category> categoryOptionalMockData = getCategoryMockDataWithZeroProducts();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(categoryOptionalMockData);
        double expectedTotalCost = 0.0;

        //Act
        double actualTotalCost = categoryService.getTotalPriceForCategory(categoryId);

        //Assert
        //checks
        Assertions.assertEquals(actualTotalCost,expectedTotalCost);
        Mockito.verify(categoryRepository).findById(categoryId);
    }

    private Optional<Category> getCategoryMockDataWithZeroProducts(){
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("CategoryName");

        List<Product> products = new ArrayList<>();

        category.setProducts(products);
        return Optional.of(category);
    }


    @Test
    public void testGetTotalPriceForOneProductUnderCategory(){
        //this method will return total cost of all products under a category
        //Arrange
        UUID categoryId = UUID.randomUUID();
        Optional<Category> categoryOptionalMockData = getCategoryMockDataWithOneProduct();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(categoryOptionalMockData);
        double expectedTotalCost = 500.00;

        //Act
        double actualTotalCost = categoryService.getTotalPriceForCategory(categoryId);

        //Assert
        Assertions.assertEquals(actualTotalCost,expectedTotalCost);
    }

    private Optional<Category> getCategoryMockDataWithOneProduct(){
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("CategoryName");

        Product product1 = new Product();
        product1.setId(UUID.randomUUID());
        product1.setTitle("Product1");
        product1.setPrice(500.00);

        List<Product> products = new ArrayList<>();
        products.add(product1);

        category.setProducts(products);
        return Optional.of(category);

    }

    @Test
    public void testCategoryNotFoundExceptionThrown(){
        //Arrange
         UUID categoryId = UUID.randomUUID();
         Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        //Act and Assert
        Assertions.assertThrows(CategoryNotFoundException.class,
                () -> categoryService.getTotalPriceForCategory(categoryId));

    }

}
