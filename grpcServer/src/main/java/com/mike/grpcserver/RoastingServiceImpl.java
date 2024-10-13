package com.mike.grpcserver;


import com.mike.RoastingServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class RoastingServiceImpl extends com.mike.RoastingServiceGrpc.RoastingServiceImplBase {

    @Override
    public void roasting(RoastingServiceOuterClass.RoastingRequest request, StreamObserver<RoastingServiceOuterClass.RoastingResponse> responseObserver) {

        System.out.println(request);

        RoastingServiceOuterClass.RoastingResponse response = RoastingServiceOuterClass
                .RoastingResponse.newBuilder()
                .setTeamUUID("a64c4df3-eb97-4ab1-b16d-0eb1dc8a2fa4")
                .setCountry("Country3")
                .setVariety("Coffee4")
                .setWeightIncome(1000)
                .setWeightOutcome(900)
                .build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }
}
