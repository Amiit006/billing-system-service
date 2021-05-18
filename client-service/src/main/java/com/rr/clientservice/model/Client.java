package com.rr.clientservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "client")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Client {

    @Id
    @Column(name = "ClientId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @Column(name = "ClientName")
    private String clientName;

    @Column(name = "Mobile")
    private String mobile;

    @Column(name = "Email")
    private  String email;

    @Column(name = "GstNumber")
    private String gstNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "addressId")
    @JsonManagedReference
    private ClientAddress address;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return clientName.equals(client.clientName) &&
                mobile.equals(client.mobile) &&
                gstNumber.equals(client.gstNumber) &&
                address.equals(client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientName, mobile, gstNumber, address);
    }
}
