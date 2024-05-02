package dev.sandeep.EcomProductServiceDec23.service;

import dev.sandeep.EcomProductServiceDec23.dto.CategoryResponseDTO;
import dev.sandeep.EcomProductServiceDec23.dto.CreateCategoryRequestDTO;
import dev.sandeep.EcomProductServiceDec23.entity.Category;
import dev.sandeep.EcomProductServiceDec23.entity.Product;
import dev.sandeep.EcomProductServiceDec23.exception.CategoryNotFoundException;
import dev.sandeep.EcomProductServiceDec23.mapper.CategoryEntityDTOMapper;
import dev.sandeep.EcomProductServiceDec23.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CategoryResponseDTO getCategory(UUID categoryId) {

        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category not found for id " + categoryId)
        );
        return CategoryEntityDTOMapper.convertCategoryEntityToCategoryResponseDTO(savedCategory);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();

        for(Category c : categories){
            categoryResponseDTOS.add(CategoryEntityDTOMapper.convertCategoryEntityToCategoryResponseDTO(c));
        }
        return categoryResponseDTOS;
    }

    @Override
    public CategoryResponseDTO createCategory(CreateCategoryRequestDTO categoryRequestDTO) {
        Category savedCategory = CategoryEntityDTOMapper.
                convertCategoryResponseDTOToCategory(categoryRequestDTO);
        savedCategory = categoryRepository.save(savedCategory);
        return CategoryEntityDTOMapper.convertCategoryEntityToCategoryResponseDTO(savedCategory);
    }

    @Override
    public CategoryResponseDTO updateCategory(CreateCategoryRequestDTO createCategoryRequestDTO, UUID categoryId) {
        return null;
    }

    @Override
    public boolean deleteCategory(UUID categoryId) {
        return false;
    }

    @Override
    public double getTotalPriceForCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category for the given id is not found")
        );
        if(category.getProducts().isEmpty()){
            return 0;
        }else{
            double sum =0;
            for(Product product : category.getProducts()){
                sum = sum + product.getPrice();
            }
            return  sum;
        }

    }
}
