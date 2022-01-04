package com.rr.purchaseservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "season")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Season {
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
    @Column(name = "CreatedDate")
    LocalDateTime createdDate;
    @Column(name = "ModifiedDate")
    LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Purchase> purchases;
}
