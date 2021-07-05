package com.rr.billingservice.model;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
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
