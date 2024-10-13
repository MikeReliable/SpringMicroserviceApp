package com.mike.clientspringmicroserviceapp.service.interf;

import com.mike.clientspringmicroserviceapp.dto.CoffeeDto;

public interface KafkaService {

    void coffeeSave(CoffeeDto coffeeDto);
}
