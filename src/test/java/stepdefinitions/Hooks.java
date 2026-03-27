package stepdefinitions;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://api.restful-api.dev";
    }
}