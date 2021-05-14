package com.rr.clientservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ClientAddress")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientAddress {

    @Id
    @Column(name = "AddressId")
    private int addressId;

    @Column(name = "AddressLine1")
    private String addressLine1;

    @Column(name = "AddressLine2")
    private String AddressLine2;

    @Column(name = "City")
    private String city;

    @Column(name = "State")
    private String state;

    @Column(name = "Country")
    private String country;

    @Column(name = "Zip")
    private String zip;

    @OneToOne(mappedBy = "address")
    @JsonBackReference
    private Client client;

}
