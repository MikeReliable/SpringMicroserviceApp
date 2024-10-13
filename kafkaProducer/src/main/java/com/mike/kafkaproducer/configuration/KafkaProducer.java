package com.mike.kafkaproducer.configuration;

import com.mike.kafkaproducer.dto.CoffeeDto;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<Long, CoffeeDto> kafkaTemplate;

    public void sendMessage(CoffeeDto message) {
        kafkaTemplate.send("coffee", message);
    }
}
