# SpringAPI Demo Project

## Overview
This project is a demo of a Spring API.
It uses RestAssured for endpoint testing and Lombok for data structures. 

For the initial iteration data is hardcoded I plan to use a MySQL database to store and retrieve data.

## Prerequisites
- **Java Development Environment**: Java JDK installed.
- **Postman**: for querying the API endpoints.

## Built With
- **Java**
- **Maven**, for dependency management
- **Spring Boot**, for the API
- **RestAssured**, for API testing
- **Spring Data JPA**, for database access
- **Lombok**, for data structures
- **Jackson**, for JSON processing

## How to run
To run the project, execute the main method from SpringApiApplication.class. This will start the Spring API on port 8080.
You can then query the API endpoints using Postman e.g. GET localhost:8080/users?id=5

## How to run tests
To run the tests simply pass ` mvn test ` to the terminal, this will run the project and all automation tests. 
