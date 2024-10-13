package com.mike.clientspringmicroserviceapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {

    @Id
    @Column(unique = true)
    private String variety;
    private String country;
    private int arabica;
    private int robusta;
    private int weight;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "coffee_roasting",
            joinColumns = {@JoinColumn(name = "variety", referencedColumnName = "variety")},
            inverseJoinColumns = {@JoinColumn(name = "roasting_id", referencedColumnName = "roastingId")}
    )
    private List<Roasting> roastingList;

}
