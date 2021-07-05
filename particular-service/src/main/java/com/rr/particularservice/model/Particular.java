package com.rr.particularservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Particulars")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Particular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ParticularId")
    private int particularId;

    @Column(name = "ParticularName")
    private String particularName;

    @Column(name = "DiscountPercentage")
    private double discountPercentage;

}
