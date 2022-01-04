package com.rr.purchaseservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "transport")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TransportId")
    int transportId;
    @Column(name="TransportName")
    String transportName;
    @Column(name="Amount")
    float amount;
    @Column(name="ConsignmentNumber")
    String consignmentNumber;

    @OneToOne(mappedBy = "transport")
    @JsonBackReference
    private Purchase purchase;
}
