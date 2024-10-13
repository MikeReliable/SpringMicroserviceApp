package com.mike.clientspringmicroserviceapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "country/variety : Country1..4/Coffee1..5")
public record CoffeeFilter(String country,
                           String variety) {

}
