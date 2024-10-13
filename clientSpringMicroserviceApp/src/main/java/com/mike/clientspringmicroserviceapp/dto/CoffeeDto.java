package com.mike.clientspringmicroserviceapp.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeDto {

    private String variety;
    private String country;
    private int arabica;
    private int robusta;
    private int weight;

}
