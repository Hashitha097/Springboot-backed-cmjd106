package com.ijse.springintro.dto;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class ProductReqDTO {
    private String name;
    private Double price;
    private String description;
    private Long categoryId;

}
