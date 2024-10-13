package com.mike.clientspringmicroserviceapp.mapper;

import com.mike.clientspringmicroserviceapp.dto.CoffeeDto;
import com.mike.clientspringmicroserviceapp.entity.Coffee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityMapper {

    Coffee toCoffee(CoffeeDto coffeeDto);

    CoffeeDto toCoffeeDto(Coffee coffee);
}
