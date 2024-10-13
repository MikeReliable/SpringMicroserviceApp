package com.mike.clientspringmicroserviceapp.repository;

import com.mike.clientspringmicroserviceapp.entity.Coffee;
import com.mike.clientspringmicroserviceapp.entity.Roasting;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CoffeeRepoTest {

    @Autowired
    private CoffeeRepo coffeeRepo;

    Coffee coffee;

    @BeforeEach
    void setup() {
        var roasting = Roasting.builder()
                .roastingId(1L)
                .teamUUID(UUID.fromString("88888888-4444-4444-4444-222222222222"))
                .country("CountryTest")
                .variety("VarietyTest")
                .weightIncome(100)
                .weightOutcome(50)
                .build();
        List<Roasting> roastingList = new ArrayList<>();
        roastingList.add(roasting);
        coffee = Coffee.builder()
                .variety("VarietyTest")
                .country("CountryTest")
                .arabica(100)
                .robusta(0)
                .weight(500)
                .roastingList(roastingList)
                .build();

        coffeeRepo.save(coffee);
    }

    @AfterEach
    void tearDown() {
        coffee = null;
        coffeeRepo.deleteAll();
    }

    // test case SUCCESS

    @Test
    void findByVariety_found() {

        Optional<Coffee> c = coffeeRepo.findByVariety("VarietyTest");
        assertThat(c).isNotEmpty();
        assertThat(c.get().getVariety()).isEqualTo(coffee.getVariety());
        assertThat(c.get().getCountry()).isEqualTo(coffee.getCountry());

    }

    // test case FAILURE

    @Test
    void findByVariety_notFound() {

        Optional<Coffee> c = coffeeRepo.findByVariety("VarietyTestNot");
        assertThat(c).isEmpty();

    }
}