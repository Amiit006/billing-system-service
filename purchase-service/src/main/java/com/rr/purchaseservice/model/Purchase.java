package com.rr.purchaseservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "purchases")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PurchaseId")
    int purchaseId;
    @Column(name="PartyName")
    String partyName;
    @Column(name="PurchaseDate")
    LocalDate purchaseDate;
    @Column(name="PurchaseAmount")
    float purchaseAmount;
    @Column(name="TaxPercent")
    float taxPercent;
    @Column(name="TaxAmount")
    float taxAmount;
    @Column(name="DiscountPercent")
    float discountPercent;
    @Column(name="DiscountAmount")
    float discountAmount;
    @Column(name="ExtraDiscountAmount")
    float extraDiscountAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seasonId")
    @JsonBackReference
    private Season season;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TransportId", referencedColumnName = "transportId")
    @JsonManagedReference
    private Transport transport;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Payment> payments;

    @Column(name="CreatedDate")
    LocalDateTime createdDate;
    @Column(name="ModifiedDate")
    LocalDateTime modifiedDate;
}
