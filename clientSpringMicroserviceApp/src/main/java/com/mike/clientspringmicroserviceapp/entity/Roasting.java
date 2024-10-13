package com.mike.clientspringmicroserviceapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Roasting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roastingId;
    private UUID teamUUID;
    private String country;
    private String variety;
    private int weightIncome;
    private int weightOutcome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "coffee_roasting",
            joinColumns = {@JoinColumn(name = "roasting_id", referencedColumnName = "roastingId")},
            inverseJoinColumns = {@JoinColumn(name = "variety", referencedColumnName = "variety")}
    )
    private Coffee coffee;

}
