package com.rr.purchaseservice.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChartResponse {

    String name;
    double value;
}