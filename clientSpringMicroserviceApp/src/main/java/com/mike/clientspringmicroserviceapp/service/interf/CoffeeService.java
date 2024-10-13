package com.mike.clientspringmicroserviceapp.service.interf;

import com.mike.clientspringmicroserviceapp.dto.CoffeeDto;
import com.mike.clientspringmicroserviceapp.dto.CoffeeFilter;
import com.mike.clientspringmicroserviceapp.dto.PageResponse;
import com.mike.clientspringmicroserviceapp.dto.RoastingLossDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CoffeeService {

    PageResponse<CoffeeDto> findAll(CoffeeFilter coffeeFilter, Pageable pageable);

    RoastingLossDto getAllByCountry(String country) throws NoSuchFieldException;

    RoastingLossDto getAllByCountryAndTeamuuid(String country, UUID teamuuid) throws NoSuchFieldException;

    RoastingLossDto getAllByVariety(String variety) throws NoSuchFieldException;

    RoastingLossDto getAllByVarietyAndTeamuuid(String variety, UUID teamuuid) throws NoSuchFieldException;
}
