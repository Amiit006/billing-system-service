package com.rr.purchaseservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PaymentId")
    String PaymentId;
    @Column(name="Amount")
    float Amount;
    @Column(name="PaymentDate")
    LocalDate PaymentDate;
    @Column(name="Mode")
    String Mode;
    @Column(name="ChequeNo")
    String ChequeNo;
    @Column(name="Remark")
    String Remark;
    @Column(name = "CreatedDate")
    LocalDateTime createdDate;
    @Column(name = "ModifiedDate")
    LocalDateTime modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PurchaseId")
    @JsonBackReference
    Purchase purchase;
}
