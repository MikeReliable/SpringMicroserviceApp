# ConsumerMicroService web application
This project presents microservice architecture based on interaction via the gRPC protocol and Kafka

## Features
* 3 separate microservices (gRPC server, kafka producer, client/consumer)
* REST architecture of client/consumer
* Connection via gRPC protocol
* Connection via message broker Kafka
* Filtering and pagination of output data
* API documentation Swagger UI
* Migrating data on first load with Liquibase
* Unit tests

## Technologies
* Java 17
* Spring Boot 3
* Spring JPA
* Maven
* PostgreSQL
* Kafka

## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+
* Kafka

To build and run the project, follow these steps:

* Clone the repository:
```sh
https://github.com/MikeReliable/SpringMicroserviceApp.git
 ```
* Go to the project directories one by one: /grpcServer; /kafkaProducer and /clientSpringMicroserviceApp 
* And build the project in each directory:
```sh
mvn clean install
 ```
* Create database 'coffeeRoast' in PostgreSQL
* Start the kafka environment: https://kafka.apache.org/quickstart
* Go to the directories one by one and run projects on local machine
```sh
mvn spring-boot:run
 ```

-> Applications run on ports:  
kafkaProducer : port = 8080  
grpcServer : port = 9000  
clientSpringMicroserviceApp : port = 8000

-> Swagger UI specification will be available at http://localhost:8000/swagger-ui/index.html
