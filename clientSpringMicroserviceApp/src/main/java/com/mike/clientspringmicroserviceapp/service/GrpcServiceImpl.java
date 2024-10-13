package com.mike.clientspringmicroserviceapp.service;

import com.mike.RoastingServiceGrpc;
import com.mike.RoastingServiceOuterClass;
import com.mike.clientspringmicroserviceapp.dto.RoastingLossDto;
import com.mike.clientspringmicroserviceapp.entity.Coffee;
import com.mike.clientspringmicroserviceapp.entity.Roasting;
import com.mike.clientspringmicroserviceapp.repository.CoffeeRepo;
import com.mike.clientspringmicroserviceapp.repository.RoastingRepo;
import com.mike.clientspringmicroserviceapp.service.interf.GrpcService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GrpcServiceImpl implements GrpcService {

    private final RoastingRepo roastingRepo;
    private final CoffeeRepo coffeeRepo;
    RoastingLossDto roastingLossDto;

    public RoastingLossDto grpcRequest() throws NoSuchFieldException {

        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:9000")
                .usePlaintext()
                .build();

        RoastingServiceGrpc.RoastingServiceBlockingStub stub = RoastingServiceGrpc.newBlockingStub(channel);

        RoastingServiceOuterClass.RoastingRequest request = RoastingServiceOuterClass.RoastingRequest
                .newBuilder().setIsRoast(true).build();

        RoastingServiceOuterClass.RoastingResponse response = stub.roasting(request);
        System.out.println(response);
        Roasting roasting = new Roasting();
        if (coffeeRepo.findByVariety(response.getVariety()).isPresent()) {
            Coffee coffee = coffeeRepo.findByVariety(response.getVariety()).get();
            int weight = coffee.getWeight();
            coffee.setWeight(weight - response.getWeightIncome());
            roasting.setCoffee(coffee);
            roasting.setTeamUUID(UUID.fromString(response.getTeamUUID()));
            roasting.setVariety(response.getVariety());
            roasting.setCountry(response.getCountry());
            roasting.setWeightIncome(response.getWeightIncome());
            roasting.setWeightOutcome(response.getWeightOutcome());
            roastingRepo.save(roasting);

            var res = (response.getWeightIncome() - response.getWeightOutcome()) * 100 / response.getWeightIncome();

            roastingLossDto = RoastingLossDto.builder()
                    .weightRaw(response.getWeightIncome())
                    .weightRoast(response.getWeightOutcome())
                    .weightLoss(res)
                    .build();
        } else {
            throw new NoSuchFieldException("Coffee not found");
        }

        channel.shutdownNow();

        return roastingLossDto;
    }
}
