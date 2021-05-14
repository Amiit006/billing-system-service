package com.rr.particularservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Particulars")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Particular {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ParticularId")
    private int particularId;

    @Column(name = "ParticularName")
    private String particularName;

}
