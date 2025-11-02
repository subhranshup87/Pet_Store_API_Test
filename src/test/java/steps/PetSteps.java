package steps;

import utils.PetPayload;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.*;

public class PetSteps {

    private String payload;
    private Response response;
    private int lastCreatedPetId;

    @Given("a pet payload with id {int}, name {string}, category {string}, and status {string}")
    public void createPetPayload(int id, String name, String category, String status) {
        payload = PetPayload.generate(id, name, category, status);
        lastCreatedPetId = id;
    }

    @Given("an updated pet payload with id {int}, name {string}, category {string}, and status {string}")
    public void updatePetPayload(int id, String name, String category, String status) {
        payload = PetPayload.generate(id, name, category, status);
        lastCreatedPetId = id;
    }

    @When("I send a POST request to pet")
    public void sendPostRequestToPet() {
        response = given()
                .baseUri("https://petstore3.swagger.io/api/v3")
                .contentType(ContentType.JSON)
                .body(payload)
                .post("/pet");
    }

    @When("I send a PUT request to pet")
    public void sendPutRequestToPet() {
        await()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    response = given()
                            .baseUri("https://petstore3.swagger.io/api/v3")
                            .contentType(ContentType.JSON)
                            .body(payload)
                            .put("/pet");

                    response.then().statusCode(200);
                });
    }


    @When("I send a GET request to pet")
    public void sendGetRequestToPet() {
        await()
                .atMost(10, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    response = given()
                            .baseUri("https://petstore3.swagger.io/api/v3")
                            .get("/pet/" + lastCreatedPetId);

                    response.then().statusCode(200);
                });
    }

    @When("I send a DELETE request to pet")
    public void sendDeleteRequestToPet() {
        response = given()
                .baseUri("https://petstore3.swagger.io/api/v3")
                .delete("/pet/" + lastCreatedPetId);
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("the response should contain name {string}")
    public void verifyName(String name) {
        response.then().body("name", equalTo(name));
    }

    @And("the response should contain name {string} and status {string}")
    public void verifyNameAndStatus(String name, String status) {
        response.then()
                .body("name", equalTo(name))
                .body("status", equalTo(status));
    }

    @And("the response should contain status {string}")
    public void verifyStatus(String status) {
        response.then().body("status", equalTo(status));
    }
}