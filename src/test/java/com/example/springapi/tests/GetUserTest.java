package com.example.springapi.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetUserTest {

    public static final String BASE_URI = "localhost:8080";

    @Test
    public void getUserTest() {
        RequestSpecification request = RestAssured.given();
        request
            .contentType("application/json");

        // Build URL to fetch user by id
        URIBuilder endpointUri = new URIBuilder()
                .setScheme("http")
                .setHost(BASE_URI)
                .setPath("/user")
                .addParameter("id", "1");
        String URL = endpointUri.toString();

        // Send GET request to get the test plan
        Response response = request
                .get(URL);

        // Assert 200 response
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        // Print response
        JsonPath jsonPath = response.jsonPath();
        System.out.println("Response: " + jsonPath.prettyPrint());
    }
}
