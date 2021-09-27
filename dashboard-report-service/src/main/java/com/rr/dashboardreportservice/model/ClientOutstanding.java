package com.rr.dashboardreportservice.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "ClientOutstanding")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ClientOutstanding {
    @Id
    private int clientId;
    private double purchasedAmount;
    private double paymentAmount;
    private LocalDateTime modifiedDate;
}
