package com.demo.taxiApp.models;

import lombok.Data;

import java.util.Map;

@Data
public class RideRequestedModel {
    String riderName;
    String riderId;
    Float lat;
    Float lon;
}
