package com.mike.clientspringmicroserviceapp.service;

import com.mike.clientspringmicroserviceapp.dto.CoffeeFilter;
import com.mike.clientspringmicroserviceapp.dto.QPredicates;
import com.mike.clientspringmicroserviceapp.entity.Coffee;
import com.mike.clientspringmicroserviceapp.entity.Roasting;
import com.mike.clientspringmicroserviceapp.mapper.EntityMapper;
import com.mike.clientspringmicroserviceapp.mapper.EntityMapperImpl;
import com.mike.clientspringmicroserviceapp.repository.CoffeeRepo;
import com.mike.clientspringmicroserviceapp.repository.RoastingRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.mike.clientspringmicroserviceapp.entity.QCoffee.coffee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoffeeServiceImplTest {

    @Mock
    private CoffeeRepo coffeeRepo;

    @Mock
    private RoastingRepo roastingRepo;

    @Mock
    private EntityMapper entityMapper;

    CoffeeServiceImpl coffeeService;
    AutoCloseable autoCloseable;
    Coffee coffeeSetup;
    Roasting roastingSetup;
    CoffeeFilter coffeeFilter;
    Pageable pageable;
    Page<Coffee> page;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        coffeeService = new CoffeeServiceImpl(coffeeRepo, roastingRepo, entityMapper);

        coffeeSetup = Coffee.builder()
                .variety("VarietyTest")
                .country("CountryTest")
                .arabica(100)
                .robusta(0)
                .weight(500)
                .build();
        coffeeRepo.save(coffeeSetup);

        roastingSetup = Roasting.builder()
                .roastingId(1L)
                .teamUUID(UUID.fromString("88888888-4444-4444-4444-222222222222"))
                .country("CountryTest")
                .variety("VarietyTest")
                .weightIncome(100)
                .weightOutcome(50)
                .coffee(coffeeSetup)
                .build();
        roastingRepo.save(roastingSetup);

        entityMapper = new EntityMapperImpl();
        entityMapper.toCoffeeDto(coffeeSetup);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    // test case SUCCESS

    @Test
    void findAll_found() {

        List<Coffee> coffeeList = new ArrayList<>();
        coffeeList.add(coffeeSetup);

        page = new PageImpl<>(coffeeList);

        coffeeFilter = new CoffeeFilter("Test", "Test");

        var predicate = QPredicates.builder()
                .add(coffeeFilter.country(), coffee.country::containsIgnoreCase)
                .add(coffeeFilter.variety(), coffee.variety::containsIgnoreCase)
                .build();

        pageable = PageRequest.of(0, 20);

        when(coffeeRepo.findAll(predicate, pageable)).thenReturn(page);
        assertThat(coffeeRepo.findAll(predicate, pageable).getTotalElements()).isEqualTo(1);
        assertThat(coffeeRepo.findAll(predicate, pageable).stream().allMatch(e -> e.getCountry().equals("CountryTest")))
                .isEqualTo(true);
        assertThat(coffeeRepo.findAll(predicate, pageable).stream().filter(e -> e.getCountry().equals("CountryTest"))
                .count()).isEqualTo(1);
        assertThat(coffeeRepo.findAll(predicate, pageable).stream().filter(e -> e.getCountry().equals("CountryTest"))
                .map(Coffee::getVariety)).contains("VarietyTest");

    }

    @Test
    void getDataFromRoastByCountry_found() {

        int[][] mas = {{roastingSetup.getWeightIncome(), roastingSetup.getWeightOutcome()}};
        when(roastingRepo.findLossByCountry(roastingSetup.getCountry())).thenReturn(mas);
        assertThat(Arrays.stream(Arrays.stream(roastingRepo.findLossByCountry("CountryTest")).findFirst().get()).sum()).isEqualTo(150);

    }

    @Test
    @Deprecated
    void getDataFromRoastByCountryAndTeamUUID_found() {

        int[][] mas = {{roastingSetup.getWeightIncome(), roastingSetup.getWeightOutcome()}};
        when(roastingRepo.findLossByCountryAndTeamUUID(roastingSetup.getCountry(), roastingSetup.getTeamUUID())).thenReturn(mas);
        assertThat(Arrays.stream(Arrays.stream(roastingRepo.findLossByCountryAndTeamUUID("CountryTest", UUID.fromString("88888888-4444-4444-4444-222222222222"))).findFirst().get()).sum()).isEqualTo(150);

    }

    @Test
    void getDataFromRoastByVariety_found() {

        int[][] mas = {{roastingSetup.getWeightIncome(), roastingSetup.getWeightOutcome()}};
        when(roastingRepo.findLossByVariety(roastingSetup.getVariety())).thenReturn(mas);
        assertThat(Arrays.stream(Arrays.stream(roastingRepo.findLossByVariety("VarietyTest")).findFirst().get()).sum()).isEqualTo(150);
    }

    @Test
    void getDataFromRoastByVarietyAndTeamUUID_found() {

        int[][] mas = {{roastingSetup.getWeightIncome(), roastingSetup.getWeightOutcome()}};
        when(roastingRepo.findLossByVarietyAndTeamUUID(roastingSetup.getVariety(), roastingSetup.getTeamUUID())).thenReturn(mas);
        assertThat(Arrays.stream(Arrays.stream(roastingRepo.findLossByVarietyAndTeamUUID("VarietyTest", UUID.fromString("88888888-4444-4444-4444-222222222222"))).findFirst().get()).sum()).isEqualTo(150);

    }

}