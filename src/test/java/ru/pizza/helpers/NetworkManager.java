package ru.pizza.helpers;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;
import com.jayway.restassured.specification.RequestSpecification;
import io.qameta.allure.Step;

import static com.jayway.restassured.RestAssured.given;

public class NetworkManager {
    private static String apiUrl = "http://localhost:8765/makepizza";
    private static RequestSpecification spec = new RequestSpecBuilder()
            .setContentType("application/json")
            .setBaseUri(apiUrl)
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .build();

    @Step("Отправка post запроса. Прокерка статускода 200")
    public static ResponseBodyExtractionOptions responsePost(Object bodyPayload){
        return given()
                .spec(spec)
                .body(bodyPayload)
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body();
    }
}