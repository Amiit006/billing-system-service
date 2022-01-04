package com.rr.purchaseservice.model.dto;

import javax.persistence.*;
import java.time.LocalDate;

public class SeasonDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeasonId")
    int seasonId;
    @Column(name = "SeasonName")
    String seasonName;
    @Column(name = "StartDate")
    LocalDate startDate;
    @Column(name = "EndDate")
    LocalDate endDate;
//    @Column(name = "CreatedDate")
//    LocalDateTime createdDate;
//    @Column(name = "ModifiedDate")
//    LocalDateTime modifiedDate;

//    @OneToMany(mappedBy = "seasonId", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Purchase> purchases;
}
