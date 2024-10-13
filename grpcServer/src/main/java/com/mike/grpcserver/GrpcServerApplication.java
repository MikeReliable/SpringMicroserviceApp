package com.mike.grpcserver;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GrpcServerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(GrpcServerApplication.class, args);

        Server server = ServerBuilder.forPort(9000)
                .addService(new RoastingServiceImpl())
                .build();

        server.start();

        System.out.println("Server started!");

        server.awaitTermination();

    }
}
