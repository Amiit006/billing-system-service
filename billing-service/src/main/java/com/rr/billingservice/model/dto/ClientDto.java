package com.rr.billingservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ClientDto {
    private int clientId;
    private String clientName;
    private String email;
    private String gstNumber;
    private boolean isActive;
    private String mobile;
    private AddressDto address;
}
