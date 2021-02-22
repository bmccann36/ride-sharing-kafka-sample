package com.demo.taxiApp.controller;

import com.demo.taxiApp.KafkaTemplateUtil;
import com.demo.taxiApp.models.RideRequestedModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("publish")
public class PublishRideRequested {

    @PostMapping(value = "/ride_requested")
    public String sendMessageToKafkaTopic(@RequestBody RideRequestedModel reqBody) {

        System.out.println("reqBody -- " + reqBody);

        KafkaTemplateUtil<RideRequestedModel> util = new KafkaTemplateUtil<>();
        KafkaTemplate<String, RideRequestedModel> kafkaTemplate = util.getKafkaTemplateForClass();

        kafkaTemplate.send("ride_requested", reqBody);

        return "success publishing to ride_requested topic";
    }
}