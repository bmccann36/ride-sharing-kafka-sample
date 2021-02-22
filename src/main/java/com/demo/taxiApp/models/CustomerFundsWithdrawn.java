package com.demo.taxiApp.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerFundsWithdrawn {
    String customerId;
    String paymentTransactionId;
    String rideId;
    Double rideDollarAmount;
}
