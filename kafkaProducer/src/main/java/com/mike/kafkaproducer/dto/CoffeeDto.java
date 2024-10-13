package com.mike.kafkaproducer.dto;

import lombok.Getter;

@Getter
public class CoffeeDto {

    private String variety;
    private String country;
    private int arabica;
    private int robusta;
    private int weight;

}
