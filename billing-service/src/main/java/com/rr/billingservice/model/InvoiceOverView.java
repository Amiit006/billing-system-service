package com.rr.billingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "InvoiceOverView")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class InvoiceOverView {
    @Id
    @Column(name="InvoiceId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceId;
    @Column(name="ClientId")
    private int clientId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentId")
    @JsonManagedReference
    private Payment payment;

    @Column(name="InvoiceDate")
    private LocalDate invoiceDate;
    @Column(name="SubTotalAmount")
    private float subTotalAmount;
    @Column(name="TaxAmount")
    private float taxAmount;
    @Column(name="GrandTotalAmount")
    private float grandTotalAmount;
    @Column(name="TaxPercentage")
    private float taxPercentage;
    @Column(name="CreatedDate")
    private LocalDateTime createdDate;
    @Column(name="ModifiedDate")
    private LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "invoiceId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<InvoiceDetails> invoiceDetails;


}
