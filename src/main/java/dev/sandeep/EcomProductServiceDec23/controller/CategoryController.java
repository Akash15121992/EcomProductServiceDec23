package dev.sandeep.EcomProductServiceDec23.controller;

import dev.sandeep.EcomProductServiceDec23.dto.CreateCategoryRequestDTO;
import dev.sandeep.EcomProductServiceDec23.dto.CategoryResponseDTO;
import dev.sandeep.EcomProductServiceDec23.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        List<CategoryResponseDTO> allCategories = categoryService.getAllCategories();
        return ResponseEntity.ok(allCategories);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable("id")UUID categoryId){
       CategoryResponseDTO categoryResponseDTO = categoryService.getCategory(categoryId);
       return  ResponseEntity.ok(categoryResponseDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory
            (@RequestBody CreateCategoryRequestDTO createCategoryRequestDTO){
       CategoryResponseDTO categoryResponseDTO = categoryService.createCategory(createCategoryRequestDTO);
       return ResponseEntity.ok(categoryResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable("id")UUID categoryId ,
                                                              @RequestBody CreateCategoryRequestDTO createCategoryRequestDTO){
        CategoryResponseDTO categoryResponseDTO =
                categoryService.updateCategory(createCategoryRequestDTO, categoryId);
        return ResponseEntity.ok(categoryResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id")UUID categoryId){
             return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }

    @GetMapping("/totalPrice/{categoryId}")
    public  ResponseEntity<Double> getTotalPriceForAllProducts(@PathVariable("categoryId") UUID categoryId){
            return  ResponseEntity.ok(categoryService.getTotalPriceForCategory(categoryId));
    }

}
