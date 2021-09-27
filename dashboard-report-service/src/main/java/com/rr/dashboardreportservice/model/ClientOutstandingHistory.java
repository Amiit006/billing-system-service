package com.rr.dashboardreportservice.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "ClientOutstandingHistory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class ClientOutstandingHistory {
    @Id
    private int clientId;
    private double purchasedAmount;
    private double paymentAmount;
    private LocalDateTime createdDate;
}
