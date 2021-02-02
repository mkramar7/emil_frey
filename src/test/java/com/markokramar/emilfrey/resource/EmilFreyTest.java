package com.markokramar.emilfrey.resource;

import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.markokramar.emilfrey.test.util.TestUtil.*;
import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public class EmilFreyTest {

    @BeforeClass
    public static void setUp() {
        // Create 'Saloon' car category
        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(CATEGORY_SALOON_JSON)
                .when()
                .post("http://localhost:8080/emilfrey/api/car_categories")
                .then()
                .statusCode(200);

        // Create 'Cabriolet' car category
        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(CATEGORY_CABRIOLET_JSON)
                .when()
                .post("http://localhost:8080/emilfrey/api/car_categories")
                .then()
                .statusCode(200);

        // Create 'BMW' car
        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(CAR_BMW_JSON)
                .when()
                .post("http://localhost:8080/emilfrey/api/cars")
                .then()
                .statusCode(200);

        // Create 'Mercedes' car
        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(CAR_MERCEDES_JSON)
                .when()
                .post("http://localhost:8080/emilfrey/api/cars")
                .then()
                .statusCode(200);

        // Create 'John Doe' lead
        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(LEAD_JOHN_DOE_JSON)
                .when()
                .post("http://localhost:8080/emilfrey/api/leads")
                .then()
                .statusCode(200);

        // Create 'Jessica Doe' lead
        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(LEAD_JESSICA_DOE_JSON)
                .when()
                .post("http://localhost:8080/emilfrey/api/leads")
                .then()
                .statusCode(200);
    }

    @Test
    public void testContentTypeGetAllCars() {
        expect().statusCode(200).contentType(ContentType.JSON).when().get("http://localhost:8080/emilfrey/api/cars");
    }

    @Test
    public void testContentTypeGetAllCarCategories() {
        expect().statusCode(200).contentType(ContentType.JSON).when().get("http://localhost:8080/emilfrey/api/car_categories");
    }

    @Test
    public void testContentTypeGetAllLeads() {
        expect().statusCode(200).contentType(ContentType.JSON).when().get("http://localhost:8080/emilfrey/api/leads");
    }


}
