package com.rr.clientservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @Column(name = "ClientId")
    private int clientId;

    @Column(name = "ClientName")
    private String clientName;

    @Column(name = "Mobile")
    private int mobile;

    @Column(name = "Email")
    private  String email;

    @Column(name = "GstNumber")
    private String gstNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "addressId")
    @JsonManagedReference
    private ClientAddress address;

}
