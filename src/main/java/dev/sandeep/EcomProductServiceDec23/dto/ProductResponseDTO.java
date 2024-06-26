package dev.sandeep.EcomProductServiceDec23.dto;

import dev.sandeep.EcomProductServiceDec23.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class ProductResponseDTO implements Serializable {
    //private int productId;
    private UUID productId;
    private String title;
    private double price;
    private String description;
    private String category;
    private String imageURL;
    private double rating;
}