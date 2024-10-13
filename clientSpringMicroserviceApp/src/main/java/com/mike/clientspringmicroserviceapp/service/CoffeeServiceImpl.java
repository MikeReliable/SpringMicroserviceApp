package com.mike.clientspringmicroserviceapp.service;

import com.mike.clientspringmicroserviceapp.dto.*;
import com.mike.clientspringmicroserviceapp.mapper.EntityMapper;
import com.mike.clientspringmicroserviceapp.repository.CoffeeRepo;
import com.mike.clientspringmicroserviceapp.repository.RoastingRepo;
import com.mike.clientspringmicroserviceapp.service.interf.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.mike.clientspringmicroserviceapp.entity.QCoffee.coffee;

@Service
@RequiredArgsConstructor
public class CoffeeServiceImpl implements CoffeeService {

    private final CoffeeRepo coffeeRepo;
    private final RoastingRepo roastingRepo;
    private final EntityMapper entityMapper;

    @Override
    public PageResponse<CoffeeDto> findAll(CoffeeFilter coffeeFilter, Pageable pageable) {
        var predicate = QPredicates.builder()
                .add(coffeeFilter.country(), coffee.country::containsIgnoreCase)
                .add(coffeeFilter.variety(), coffee.variety::containsIgnoreCase)
                .build();

        return PageResponse.of(coffeeRepo.findAll(predicate, pageable)
                .map(entityMapper::toCoffeeDto));
    }

    @Override
    public RoastingLossDto getAllByCountry(String country) throws NoSuchFieldException {
        int weightIncome = Arrays.stream(getDataFromRoastByCountry(country)).flatMapToInt(c -> IntStream.of(c[0])).sum();
        int weightOutcome = Arrays.stream(getDataFromRoastByCountry(country)).flatMapToInt(c -> IntStream.of(c[1])).sum();
        return getRoastingLossDto(weightIncome, weightOutcome);
    }

    @Override
    public RoastingLossDto getAllByCountryAndTeamuuid(String country, UUID teamuuid) throws NoSuchFieldException {
        int weightIncome = Arrays.stream(getDataFromRoastByCountryAndTeamUUID(country, teamuuid)).flatMapToInt(c -> IntStream.of(c[0])).sum();
        int weightOutcome = Arrays.stream(getDataFromRoastByCountryAndTeamUUID(country, teamuuid)).flatMapToInt(c -> IntStream.of(c[1])).sum();
        return getRoastingLossDto(weightIncome, weightOutcome);
    }

    @Override
    public RoastingLossDto getAllByVariety(String variety) throws NoSuchFieldException {
        int weightIncome = Arrays.stream(getDataFromRoastByVariety(variety)).flatMapToInt(c -> IntStream.of(c[0])).sum();
        int weightOutcome = Arrays.stream(getDataFromRoastByVariety(variety)).flatMapToInt(c -> IntStream.of(c[1])).sum();
        return getRoastingLossDto(weightIncome, weightOutcome);
    }

    @Override
    public RoastingLossDto getAllByVarietyAndTeamuuid(String variety, UUID teamuuid) throws NoSuchFieldException {
        int weightIncome = Arrays.stream(getDataFromRoastByVarietyAndTeamUUID(variety, teamuuid)).flatMapToInt(c -> IntStream.of(c[0])).sum();
        int weightOutcome = Arrays.stream(getDataFromRoastByVarietyAndTeamUUID(variety, teamuuid)).flatMapToInt(c -> IntStream.of(c[1])).sum();
        return getRoastingLossDto(weightIncome, weightOutcome);
    }

    private RoastingLossDto getRoastingLossDto(int weightIncome, int weightOutcome) {
        RoastingLossDto roastingLossDto = new RoastingLossDto();
        roastingLossDto.setWeightRaw(weightIncome);
        roastingLossDto.setWeightRoast(weightOutcome);
        roastingLossDto.setWeightLoss((float) (weightIncome - weightOutcome) * 100 / (weightIncome));
        return roastingLossDto;
    }

    public int[][] getDataFromRoastByCountry(String country) throws NoSuchFieldException {
        int[][] data = roastingRepo.findLossByCountry(country);
        if (data.length == 0) {
            throw new NoSuchFieldException("Country not found");
        }
        return data;
    }

    public int[][] getDataFromRoastByCountryAndTeamUUID(String country, UUID teamuuid) throws NoSuchFieldException {
        int[][] data = roastingRepo.findLossByCountryAndTeamUUID(country, teamuuid);
        if (data.length == 0) {
            throw new NoSuchFieldException("Country or Team not found");
        }
        return data;
    }

    public int[][] getDataFromRoastByVariety(String variety) throws NoSuchFieldException {
        int[][] data = roastingRepo.findLossByVariety(variety);
        if (data.length == 0) {
            throw new NoSuchFieldException("Variety not found");
        }
        return data;
    }

    public int[][] getDataFromRoastByVarietyAndTeamUUID(String variety, UUID teamuuid) throws NoSuchFieldException {
        int[][] data = roastingRepo.findLossByVarietyAndTeamUUID(variety, teamuuid);
        if (data.length == 0) {
            throw new NoSuchFieldException("Variety  or Team not found");
        }
        return data;
    }

}
