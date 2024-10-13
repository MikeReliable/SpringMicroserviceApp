package com.mike.clientspringmicroserviceapp.service;

import com.mike.clientspringmicroserviceapp.dto.CoffeeDto;
import com.mike.clientspringmicroserviceapp.entity.Coffee;
import com.mike.clientspringmicroserviceapp.mapper.EntityMapper;
import com.mike.clientspringmicroserviceapp.repository.CoffeeRepo;
import com.mike.clientspringmicroserviceapp.service.interf.KafkaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private CoffeeRepo coffeeRepo;
    private EntityMapper entityMapper;

    public void coffeeSave(CoffeeDto coffeeDto) {
        Coffee coffee = entityMapper.toCoffee(coffeeDto);
        if (coffeeRepo.findByVariety(coffee.getVariety()).isPresent()) {
            Coffee coffeeDb = coffeeRepo.findByVariety(coffee.getVariety()).get();
            int weight = coffeeDb.getWeight() + coffee.getWeight();
            coffeeDb.setWeight(weight);
            coffeeRepo.save(coffeeDb);
        } else {
            coffeeRepo.save(coffee);
        }
    }
}
