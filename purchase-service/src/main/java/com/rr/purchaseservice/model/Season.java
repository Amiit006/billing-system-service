package com.rr.purchaseservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd[ ][HH][:mm][:ss][.SSS][.SS][.S]")
    LocalDate startDate;
    @Column(name = "EndDate")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd[ ][HH][:mm][:ss][.SSS][.SS][.S]")
    LocalDate endDate;
    @Column(name = "CreatedDate")
    LocalDateTime createdDate;
    @Column(name = "ModifiedDate")
    LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Purchase> purchases;
}
