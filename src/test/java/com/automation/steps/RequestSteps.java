package com.automation.steps;

import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class RequestSteps {

    @Given("user can calls the {string} endpoint")
    public void user_can_calls_the_endpoint(String endpoint) {
        RestAssuredUtils.setEndPoint(endpoint);
    }

    @And("sets the header as {string} and {string}")
    public void sets_the_header_as_and(String key, String value) {
        RestAssuredUtils.setHeader(key, value);
    }

    @And("sets the body from the file {string}")
    public void sets_the_body_from_the_file(String fileName) {
        RestAssuredUtils.setBody(fileName);
    }

    @When("user performs the post call")
    public void user_performs_the_post_call() {
        RestAssuredUtils.post();
    }


    @And("sets the body from the file {string} of credentials {string} and {string}")
    public void setsTheBodyFromTheFileOfCredentialsAnd(String fileName, String email, String password) {
        RestAssuredUtils.setBody(fileName, email, password);
    }

    @When("user performs the get call")
    public void userPerformsTheGetCall() {
        RestAssuredUtils.get();
    }

    @And("sets the body from the file {string} for update")
    public void setsTheBodyFromTheFileForUpdate(String fileName) {
        RestAssuredUtils.setBodyForUpdate(fileName);
    }

    @When("user performs the put call")
    public void userPerformsThePutCall() {
        RestAssuredUtils.put();
    }

    @When("user performs the delete call")
    public void userPerformsTheDeleteCall() {
        RestAssuredUtils.delete();
    }
}
