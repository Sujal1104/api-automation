package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ObjectAPI {

    public static Response createObject(Object payload) {
        return given()
                .header("Content-Type", "application/json")
                .body(payload)
                .post("/objects");
    }

    public static Response getObject(String id) {
        return given().get("/objects/" + id);
    }

    public static Response deleteObject(String id) {
        return given().delete("/objects/" + id);
    }
}