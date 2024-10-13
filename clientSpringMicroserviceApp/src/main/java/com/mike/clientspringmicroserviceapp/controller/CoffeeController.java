package com.mike.clientspringmicroserviceapp.controller;

import com.mike.clientspringmicroserviceapp.dto.RoastingLossDto;
import com.mike.clientspringmicroserviceapp.service.interf.GrpcService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CoffeeController {

    private final GrpcService grpcService;

    @PostMapping("/grpc")
    @Operation(summary = "Just example to start connection by gRPC")
    public ResponseEntity<RoastingLossDto> grpcRequest() throws NoSuchFieldException {
        return new ResponseEntity<>(grpcService.grpcRequest(), HttpStatus.OK);
    }
}
