package dev.sandeep.EcomProductServiceDec23.controller;

import dev.sandeep.EcomProductServiceDec23.dto.CategoryResponseDTO;
import dev.sandeep.EcomProductServiceDec23.dto.CreateCategoryRequestDTO;
import dev.sandeep.EcomProductServiceDec23.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this); // not required nowadays. It initialises and adds all the required mocks
    }

    @Test
    public void testUpdateCategorySuccess(){

        //Arrange
        CreateCategoryRequestDTO updateRequestDTO = new CreateCategoryRequestDTO();
        updateRequestDTO.setCategoryName("NewCategoryName");

        UUID categoryId = UUID.randomUUID();
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategoryId(categoryId);
        categoryResponseDTO.setCategoryName("NewCategoryName");

        Mockito.when(categoryService.updateCategory(updateRequestDTO,categoryId)).
                thenReturn(categoryResponseDTO);
        //Act
        ResponseEntity<CategoryResponseDTO> categoryResponseEntity = categoryController
                .updateCategory(categoryId,updateRequestDTO);

        //Assert
        Assertions.assertEquals(categoryResponseDTO,categoryResponseEntity.getBody());

    }

    @Test
    public void testCreateCategory(){

        //Arrange
        CreateCategoryRequestDTO createCategoryRequestDTO = new CreateCategoryRequestDTO();
        createCategoryRequestDTO.setCategoryName("NewCategoryName");

        UUID categoryId = UUID.randomUUID();
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategoryName("NewCategoryName");
        categoryResponseDTO.setCategoryId(categoryId);

        Mockito.when(categoryService.createCategory(createCategoryRequestDTO))
                .thenReturn(categoryResponseDTO);

        //Act
        ResponseEntity<CategoryResponseDTO> categoryResponseDTOResponseEntity = categoryController.
                createCategory(createCategoryRequestDTO);

        //Assert
        Assertions.assertEquals(categoryResponseDTO,categoryResponseDTOResponseEntity.getBody());
    }

    @Test
    public void testGetCategoryById(){

        //Arrange
        UUID categoryId = UUID.randomUUID();
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategoryId(categoryId);
        categoryResponseDTO.setCategoryName("NewCategoryName");

        Mockito.when(categoryService.getCategory(categoryId)).thenReturn(categoryResponseDTO);

        //Act
        ResponseEntity<CategoryResponseDTO> categoryResponseDTOResponseEntity = categoryController
                .getCategoryById(categoryId);

        //Assert
        Assertions.assertEquals(categoryResponseDTO,categoryResponseDTOResponseEntity.getBody());

    }

    @Test
    public void getTotalPriceForAllProducts(){

        //Arrange
         double price = 0.0;
         UUID categoryId = UUID.randomUUID();
         Mockito.when(categoryService.getTotalPriceForCategory(categoryId)).thenReturn(price);

         //Act
         ResponseEntity<Double> doubleResponseEntity = categoryController.getTotalPriceForAllProducts(categoryId);

         //Assert
        Assertions.assertEquals(price,doubleResponseEntity.getBody());

    }

    @Test
    public void testGetdeleteCategory(){

        //Arrange
        Boolean status = true;
        UUID categoryId = UUID.randomUUID();
        Mockito.when(categoryService.deleteCategory(categoryId)).thenReturn(status);

        //Act
        ResponseEntity<Boolean> booleanResponseEntity = categoryController.
                deleteCategory(categoryId);

        //Assert
        Assertions.assertEquals(status,booleanResponseEntity.getBody());

    }
}
