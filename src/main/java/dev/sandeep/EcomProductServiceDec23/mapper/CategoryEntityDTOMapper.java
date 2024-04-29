package dev.sandeep.EcomProductServiceDec23.mapper;

import dev.sandeep.EcomProductServiceDec23.dto.CategoryResponseDTO;
import dev.sandeep.EcomProductServiceDec23.dto.CreateCategoryRequestDTO;
import dev.sandeep.EcomProductServiceDec23.dto.ProductResponseDTO;
import dev.sandeep.EcomProductServiceDec23.entity.Category;
import dev.sandeep.EcomProductServiceDec23.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryEntityDTOMapper {

    public static CategoryResponseDTO convertCategoryEntityToCategoryResponseDTO(Category category){
        //call to productentitydto mapper to convert product entity to product response dto
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        //categoryResponseDTO.setCategoryId(category.getId());
        categoryResponseDTO.setCategoryName(category.getName());
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        if(!(category.getProducts() == null || category.getProducts().isEmpty())){
            for(Product p : category.getProducts()){
                productResponseDTOS.add(ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(p));
            }
        }
        categoryResponseDTO.setProducts(productResponseDTOS);
        return  categoryResponseDTO;
    }

    public static Category convertCategoryResponseDTOToCategory(CreateCategoryRequestDTO categoryRequestDTO){
        Category category = new Category();
        category.setName(categoryRequestDTO.getCategoryName());
        return category;
    }
}
