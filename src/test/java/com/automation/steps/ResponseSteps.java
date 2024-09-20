package com.automation.steps;

import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class ResponseSteps {

    @Then("verify user can get a status code of {int}")
    public void verify_user_can_get_a_status_code_of(int statusCode) {
        Assert.assertEquals(statusCode, RestAssuredUtils.getStatusCode());
    }

    @Then("verify the user can get a {string}")
    public void verify_the_user_can_get_a_value(String key) {
        Assert.assertFalse(RestAssuredUtils.getResponseValue(key));
    }

    @And("verify the user can get a message of {string}")
    public void verifyTheUserCanGetAMessageOf(String message) {
        Assert.assertEquals(message, RestAssuredUtils.getResponseMsg());
    }
}
