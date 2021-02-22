package com.demo.taxiApp.config;

import com.demo.taxiApp.models.RideRequestedModel;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class RideRequestedConsumer {

    @Autowired
    ConsumerCreator consumerCreator;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RideRequestedModel>
    rideRequestedListenerTwo() {
        return consumerCreator.createCustomListener(
                "specialId",
                new TypeReference<RideRequestedModel>() {
                });
    }

}
