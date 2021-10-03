package com.rr.dashboardreportservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ClientDto {
    int clientId;
    String clientName;
    String mobile;
    String email;
}
