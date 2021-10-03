package com.rr.clientservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clientaddress")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ClientAddress {

    @Id
    @Column(name = "AddressId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @Column(name = "StoreName")
    private String storeName;

    @Column(name = "AddressLine1")
    private String addressLine1;

    @Column(name = "AddressLine2")
    private String addressLine2;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAddress that = (ClientAddress) o;
        return storeName.equals(that.storeName) &&
                city.equals(that.city) &&
                state.equals(that.state) &&
                country.equals(that.country) &&
                zip.equals(that.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeName, city, state, country, zip);
    }
}
