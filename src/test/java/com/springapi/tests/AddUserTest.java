//package com.springapi.tests;
//
//import io.restassured.response.Response;
//import io.restassured.response.ValidatableResponse;
//import org.junit.jupiter.api.Test;
//
//import static io.restassured.RestAssured.given;
//
//public class AddUserTest {
//
//    @Test
//    public void addUserTest() {
//
//        Response response;
//        response = given()
//                .contentType("application/json")
//                .body(temp)
//                .then()
//                .when()
//                .post("/user/add");
//
//        // Response
//        System.out.println("Response: " + response.asString());
//
//        // Validate Response
//        ValidatableResponse validatableResponse = response.then();
//        validatableResponse.statusCode(201);
//    }
//}
