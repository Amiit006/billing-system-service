package com.rr.billingservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "InvoiceDetails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class InvoiceDetails {
    @Id
    @Column(name = "InvoiceDetailsId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceDetailsId;
    @Column(name = "SlNo")
    private int slNo;
    @Column(name = "Perticulars")
    private String perticulars;
    @Column(name = "Amount")
    private float amount;
    @Column(name = "Quanity")
    private int quanity;
    @Column(name = "Total")
    private float total;
    @Column(name = "QuantityType")
    private String quantityType;
    @Column(name = "Verified")
    private boolean verified;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoiceId")
    @JsonBackReference
    private InvoiceOverView invoiceId;

    @Column(name = "CreatedDate")
    private LocalDateTime createdDate;
    @Column(name = "ModifiedDate")
    private LocalDateTime modifiedDate;
}
