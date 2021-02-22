package com.demo.taxiApp.config;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ConsumerCreator {

    @Bean
    public <T> ConsumerFactory<String, T> getCustomFactory(String consumerId, TypeReference<T> deSerlType) {

        Map<String, Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        map.put(ConsumerConfig.GROUP_ID_CONFIG, "id");
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        // return message in JSON format
        return new DefaultKafkaConsumerFactory<String, T>(
                map,
                new StringDeserializer(),
                new JsonDeserializer<>(deSerlType));
    }


    @Bean
    public <T> ConcurrentKafkaListenerContainerFactory<String, T> createCustomListener(String consumerId, TypeReference<T> deSerlType) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        ConsumerFactory<String, T> consumerFactory =
                getCustomFactory(
                        consumerId,
                        deSerlType
                );

        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
