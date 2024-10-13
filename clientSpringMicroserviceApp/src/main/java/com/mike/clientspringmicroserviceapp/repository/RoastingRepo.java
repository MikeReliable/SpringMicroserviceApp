package com.mike.clientspringmicroserviceapp.repository;

import com.mike.clientspringmicroserviceapp.entity.Roasting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface RoastingRepo extends JpaRepository<Roasting, UUID> {

    @Query(value = "select r.weight_income, r.weight_outcome from roasting r where r.country= :country", nativeQuery = true)
    int[][] findLossByCountry(String country);

    @Query(value = "select r.weight_income, r.weight_outcome from roasting r where r.country= :country and r.teamuuid= :teamuuid", nativeQuery = true)
    int[][] findLossByCountryAndTeamUUID(String country, UUID teamuuid);

    @Query(value = "select r.weight_income, r.weight_outcome from roasting r where r.variety= :variety", nativeQuery = true)
    int[][] findLossByVariety(String variety);

    @Query(value = "select r.weight_income, r.weight_outcome from roasting r where r.variety= :variety and r.teamuuid= :teamuuid", nativeQuery = true)
    int[][] findLossByVarietyAndTeamUUID(String variety, UUID teamuuid);
}
