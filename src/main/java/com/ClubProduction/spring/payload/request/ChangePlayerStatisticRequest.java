package com.ClubProduction.spring.payload.request;

import lombok.Data;

@Data
public class ChangePlayerStatisticRequest {
    private int goals;
    private int assist;

}
