package com.eliseev.app.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderDTO {

    private RouteDTO routeDTO;
    private String secondName;
    private String firstName;
    private String email;

    private Double cost;
    private Double coefficient;

    private List<ProductDTO> productDTOs;
    /*private Map<Product, Double> productWeight;*/
}
