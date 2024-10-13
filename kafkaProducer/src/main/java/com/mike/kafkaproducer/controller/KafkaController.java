package com.mike.kafkaproducer.controller;

import com.mike.kafkaproducer.configuration.KafkaProducer;
import com.mike.kafkaproducer.dto.CoffeeDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/kafka/send")
    public String send(@RequestBody CoffeeDto message) {
        kafkaProducer.sendMessage(message);
        return "success";
    }

}
