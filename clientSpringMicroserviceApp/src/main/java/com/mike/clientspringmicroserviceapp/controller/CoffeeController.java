package com.mike.clientspringmicroserviceapp.controller;

import com.mike.clientspringmicroserviceapp.dto.CoffeeDto;
import com.mike.clientspringmicroserviceapp.dto.CoffeeFilter;
import com.mike.clientspringmicroserviceapp.dto.PageResponse;
import com.mike.clientspringmicroserviceapp.dto.RoastingLossDto;
import com.mike.clientspringmicroserviceapp.service.interf.CoffeeService;
import com.mike.clientspringmicroserviceapp.service.interf.GrpcService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Coffee storage ", description = "Getting information about coffee residues")
public class CoffeeController {

    private final CoffeeService coffeeService;
    private final GrpcService grpcService;

    @PostMapping("/grpc")
    @Operation(summary = "Just example to start connection by gRPC")
    public ResponseEntity<RoastingLossDto> grpcRequest() throws NoSuchFieldException {
        return new ResponseEntity<>(grpcService.grpcRequest(), HttpStatus.OK);
    }

    @GetMapping("/store")
    @Operation(summary = "Get all residues on store")
    public ResponseEntity<PageResponse<CoffeeDto>> getAllByFilter(CoffeeFilter coffeeFilter,
                                                                  @ParameterObject Pageable pageable) {
        return new ResponseEntity<>(coffeeService.findAll(coffeeFilter, pageable), HttpStatus.OK);
    }

    @GetMapping("/country")
    @Operation(summary = "Get all residues by Country")
    public ResponseEntity<RoastingLossDto> getAllByCountry(@RequestParam(value = "country") @Parameter(name = "country", example = "Country1..4") String country) throws NoSuchFieldException {
        return new ResponseEntity<>(coffeeService.getAllByCountry(country), HttpStatus.OK);
    }

    @GetMapping("/country/{teamuuid}")
    @Operation(summary = "Get all residues by Country and Team")
    public ResponseEntity<RoastingLossDto> getAllByCountryAndTeamuuid(@PathVariable @Parameter(name = "teamuuid", example = "0393e6e3-6ad5-43e4-9aad-1607d9110dac") UUID teamuuid,
                                                                      @RequestParam(value = "country") @Parameter(name = "country", example = "Country1..4") String country) throws NoSuchFieldException {
        return new ResponseEntity<>(coffeeService.getAllByCountryAndTeamuuid(country, teamuuid), HttpStatus.OK);
    }

    @GetMapping("/variety")
    @Operation(summary = "Get all residues by Variety")
    public ResponseEntity<RoastingLossDto> getAllByVariety(@RequestParam(value = "variety") @Parameter(name = "variety", example = "Coffee1..5") String variety) throws NoSuchFieldException {
        return new ResponseEntity<>(coffeeService.getAllByVariety(variety), HttpStatus.OK);
    }

    @GetMapping("/variety/{teamuuid}")
    @Operation(summary = "Get all residues by Variety and Team")
    public ResponseEntity<RoastingLossDto> getAllByVarietyAndTeamuuid(@PathVariable @Parameter(name = "teamuuid", example = "0393e6e3-6ad5-43e4-9aad-1607d9110dac") UUID teamuuid,
                                                                      @RequestParam(value = "variety") @Parameter(name = "variety", example = "Coffee1..5") String variety) throws NoSuchFieldException {
        return new ResponseEntity<>(coffeeService.getAllByVarietyAndTeamuuid(variety, teamuuid), HttpStatus.OK);
    }

}
