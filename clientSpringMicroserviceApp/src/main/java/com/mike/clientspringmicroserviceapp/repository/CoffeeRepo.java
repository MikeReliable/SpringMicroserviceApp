package com.mike.clientspringmicroserviceapp.repository;

import com.mike.clientspringmicroserviceapp.entity.Coffee;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

@Primary
public interface CoffeeRepo extends JpaRepository<Coffee, String>, QuerydslPredicateExecutor<Coffee> {

    Optional<Coffee> findByVariety(String variety);

}
