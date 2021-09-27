package com.rr.dashboardreportservice.model;

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
    private double subTotalAmount;
    @Column(name="TaxPercentage")
    private double taxPercentage;
    @Column(name="TaxAmount")
    private double taxAmount;
    @Column(name="DiscountPercentage")
    private double discountPercentage;
    @Column(name="DiscountAmount")
    private double discountAmount;
    @Column(name="GrandTotalAmount")
    private double grandTotalAmount;
    @Column(name="CreatedDate")
    private LocalDateTime createdDate;
    @Column(name="ModifiedDate")
    private LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "invoiceId", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<InvoiceDetails> invoiceDetails;


}
