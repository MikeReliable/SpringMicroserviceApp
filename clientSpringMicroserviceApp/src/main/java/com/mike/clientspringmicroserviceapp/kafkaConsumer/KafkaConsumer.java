package com.mike.clientspringmicroserviceapp.kafkaConsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mike.clientspringmicroserviceapp.dto.CoffeeDto;
import com.mike.clientspringmicroserviceapp.service.interf.KafkaService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumer {

    private KafkaService kafkaService;

    @KafkaListener(topics = "coffee", groupId = "coffee_consumer")
    public void coffeeGet(String message) {
        System.out.println("Received message:" + message);
        CoffeeDto coffeeDto = new CoffeeDto();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            coffeeDto = objectMapper.readValue(message, CoffeeDto.class);
        } catch (JsonProcessingException e) {
            System.out.println("message format is invalid");
        }
        kafkaService.coffeeSave(coffeeDto);
    }
}
