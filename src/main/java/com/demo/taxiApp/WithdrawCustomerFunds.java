package com.demo.taxiApp;

import com.demo.taxiApp.models.CustomerFundsWithdrawn;
import com.demo.taxiApp.models.RideRequestedModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WithdrawCustomerFunds {
    @KafkaListener(topics = "ride_requested",
            groupId = "specialId", containerFactory
            = "rideRequestedListenerTwo")
    public void
    consume(RideRequestedModel rideRequestedEvent) {
        System.out.println("recieved ride requested event");
        System.out.println("processing customer funds");

        // ? mock funds withdrawn event
        CustomerFundsWithdrawn fundsEvent = new CustomerFundsWithdrawn(
                "cust1",
                "transaction01",
                "ride01",
                18.49
        );
        KafkaTemplateUtil<CustomerFundsWithdrawn> util = new KafkaTemplateUtil<>();
        KafkaTemplate<String, CustomerFundsWithdrawn> kafkaTemplate = util.getKafkaTemplateForClass();
        // ? publish back to Kafka
        log.info("sending customer funds withdrawn message");
        kafkaTemplate.send("cust_funds_withdrawn", fundsEvent);
    }
}



















