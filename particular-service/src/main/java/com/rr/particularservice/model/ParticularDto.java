package com.rr.particularservice.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class ParticularDto {

    private String particularName;
    private double discountPercentage;
}
