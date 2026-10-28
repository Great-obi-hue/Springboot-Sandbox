package com.great.clients.fraud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FraudCheckResponse {
    private boolean isFraudster; // Change Boolean to primitive boolean
}
