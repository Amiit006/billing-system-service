package com.rr.billingservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentId")
    private int paymentId;
    @Column(name = "ClientId")
    private int clientId;
    @Column(name = "Amount")
    private double amount;
    @Column(name = "PaymentMode")
    private String paymentMode;
    @Column(name = "PaymentDate")
    private LocalDate paymentDate;
    @Column(name = "CreatedDate")
    private LocalDateTime createdDate;
    @Column(name = "ModifiedDate")
    private LocalDateTime modifiedDate;

    @OneToOne(mappedBy = "payment")
    @JsonBackReference
    private InvoiceOverView invoiceOverView;
}
