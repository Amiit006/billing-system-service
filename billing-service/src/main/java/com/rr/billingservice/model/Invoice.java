package com.rr.billingservice.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Invoices")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Invoice {

    @Id
    @Column(name = "InvoiceId")
    private int invoiceId;
    @Column(name = "ClientId")
    private int clientId;
    @Column(name = "PaymentId")
    private int paymentId;
    @Column(name = "createdDate")
    private LocalDateTime CreatedDate;
    @Column(name = "modifiedDate")
    private LocalDateTime ModifiedDate;
}
