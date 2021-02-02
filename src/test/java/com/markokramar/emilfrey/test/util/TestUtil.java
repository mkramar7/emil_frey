package com.markokramar.emilfrey.test.util;

public class TestUtil {
    public static final String CATEGORY_SALOON_JSON = "{ \"categoryName\" : \"Saloon\" }";
    public static final String CATEGORY_CABRIOLET_JSON = "{ \"categoryName\" : \"Cabriolet\" }";

    public static final String CAR_BMW_JSON= "{ \"manufacturer\" : \"BMW\", \"model\" : \"3 Series\", \"modelYear\" : 1998, " +
            "\"category\" : { \"id\" : 1, \"categoryName\" : \"Saloon\" } }";
    public static final String CAR_MERCEDES_JSON = "{ \"manufacturer\" : \"Mercedes\", \"model\" : \"CLK 320\", \"modelYear\" : 2015, " +
            "\"category\" : { \"id\" : 2, \"categoryName\" : \"Cabriolet\" } }";

    public static final String LEAD_JOHN_DOE_JSON = "{ \"firstName\" : \"John\", \"lastName\" : \"Doe\", \"carsOfInterest\": [" +
            "{ \"id\": 3, \"manufacturer\" : \"BMW\", \"model\" : \"3 Series\", \"modelYear\" : 1998, " +
            "\"category\" : { \"id\" : 1, \"categoryName\" : \"Saloon\" } }"
            + "] }";
    public static final String LEAD_JESSICA_DOE_JSON = "{ \"firstName\" : \"Jessica\", \"lastName\" : \"Doe\", \"carsOfInterest\": [" +
            "{ \"id\": 4, \"manufacturer\" : \"Mercedes\", \"model\" : \"CLK 320\", \"modelYear\" : 2015, " +
            "\"category\" : { \"id\" : 2, \"categoryName\" : \"Cabriolet\" } }"
            + "] }";
}
