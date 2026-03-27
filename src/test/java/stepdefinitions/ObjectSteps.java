package stepdefinitions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ObjectSteps {

    String name;
    String cpu;
    double price;
    Response response;
    static String objectId;

    String apiKey;

    @Before
    public void setup() {
        baseURI = "https://api.restful-api.dev";
        apiKey = System.getProperty("apiKey"); // optional
    }

    @Given("a {string} item is created")
    public void setName(String itemName) {
        this.name = itemName;
    }

    @And("the CPU model is {string}")
    public void setCPU(String cpuModel) {
        this.cpu = cpuModel;
    }

    @And("has a price of {string}")
    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    @When("the request to add the item is made")
    public void createObject() {

        Map<String, Object> data = new HashMap<>();
        data.put("year", 2024);
        data.put("price", price);
        data.put("CPU model", cpu);

        Map<String, Object> payload = new HashMap<>();
        payload.put("name", name);
        payload.put("data", data);

        RequestSpecification request = given().header("Content-Type", "application/json");

        if (apiKey != null) {
            request.header("x-api-key", apiKey);
        }

        response = request
                .body(payload)
                .log().all()
                .when()
                .post("/objects");

        objectId = response.jsonPath().getString("id");
    }

    @Then("a 200 response code is returned")
    public void validateStatus() {
        assertEquals(200, response.getStatusCode());
    }

    @Then("response code should be {int}")
    public void response_code_should_be(Integer expectedStatusCode) {
        assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
    }

    @Then("a {string} is created")
    public void validateName(String expectedName) {
        assertEquals(expectedName, response.jsonPath().getString("name"));
        assertNotNull(objectId);
    }

    @When("user retrieves the created object")
    public void getObject() {

        RequestSpecification request = given();

        if (apiKey != null) {
            request.header("x-api-key", apiKey);
        }

        response = request
                .log().all()
                .when()
                .get("/objects/" + objectId);
    }

    @Then("response contains correct name")
    public void validateGet() {
        assertEquals(name, response.jsonPath().getString("name"));
        assertEquals(cpu, response.jsonPath().getString("data['CPU model']"));
    }

    @When("user deletes the object")
    public void deleteObject() {

        RequestSpecification request = given();

        if (apiKey != null) {
            request.header("x-api-key", apiKey);
        }

        response = request
                .log().all()
                .when()
                .delete("/objects/" + objectId);
    }

  

    @When("user retrieves the deleted object")
    public void getDeletedObject() {

        RequestSpecification request = given();

        if (apiKey != null) {
            request.header("x-api-key", apiKey);
        }

        response = request
                .when()
                .get("/objects/" + objectId);
    }

    

    // 🔥 EDGE CASES

    @When("user retrieves object with invalid id")
    public void getInvalidObject() {

        RequestSpecification request = given();

        if (apiKey != null) {
            request.header("x-api-key", apiKey);
        }

        response = request
                .when()
                .get("/objects/invalid123");
    }

    @When("user sends request without name")
    public void createInvalidObject() {

        Map<String, Object> payload = new HashMap<>();

        RequestSpecification request = given().header("Content-Type", "application/json");

        if (apiKey != null) {
            request.header("x-api-key", apiKey);
        }

        response = request
                .body(payload)
                .when()
                .post("/objects");
    }
}