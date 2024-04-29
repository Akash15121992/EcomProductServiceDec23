package dev.sandeep.EcomProductServiceDec23.service;

import dev.sandeep.EcomProductServiceDec23.dto.CategoryResponseDTO;
import dev.sandeep.EcomProductServiceDec23.dto.CreateCategoryRequestDTO;
import dev.sandeep.EcomProductServiceDec23.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryResponseDTO getCategory(UUID categoryId);
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO createCategory(CreateCategoryRequestDTO createCategoryRequestDTO);
    CategoryResponseDTO updateCategory(CreateCategoryRequestDTO createCategoryRequestDTO,UUID categoryId);
    boolean deleteCategory(UUID categoryId);

}
