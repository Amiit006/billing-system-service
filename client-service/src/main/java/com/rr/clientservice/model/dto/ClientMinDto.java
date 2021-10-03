package com.rr.clientservice.model.dto;

import lombok.*;

@Getter
public class ClientMinDto {
    int clientId;
    String clientName;
    String mobile;
    String email;

    public ClientMinDto(int clientId, String clientName, String mobile, String email) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.mobile = mobile;
        this.email = email;
    }
}
