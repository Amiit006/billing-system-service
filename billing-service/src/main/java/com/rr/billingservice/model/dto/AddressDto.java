package com.rr.billingservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AddressDto {
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String state;
    private String storeName;
    private String zip;
}
