package dev.sandeep.EcomProductServiceDec23.mapper;

import dev.sandeep.EcomProductServiceDec23.dto.CreateProductRequestDTO;
import dev.sandeep.EcomProductServiceDec23.dto.ProductResponseDTO;
import dev.sandeep.EcomProductServiceDec23.entity.Product;

public class ProductEntityDTOMapper {

    public static ProductResponseDTO convertProductEntityToProductResponseDTO(Product product){
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setTitle(product.getTitle());
        responseDTO.setCategory(product.getCategory().getName());
        responseDTO.setRating(product.getRating());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setImageURL(product.getImageURL());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setProductId(product.getId());
        return responseDTO;
    }

    public static Product convertCreateProductRequestDTOToProduct(CreateProductRequestDTO createProductRequestDTO){
        Product product = new Product();
        product.setTitle(createProductRequestDTO.getTitle());
        product.setRating(0);
        product.setPrice(createProductRequestDTO.getPrice());
        product.setImageURL(createProductRequestDTO.getImageURL());
        product.setDescription(createProductRequestDTO.getDescription());
        //product.setCategory(createProductRequestDTO.getCategory());
        return product;
    }
}