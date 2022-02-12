package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginStepDefinitions {
    @Given("the following users exist in the system:")
    public void theFollowingUsersExistInTheSystem() {
    }

    @When("the registered user tries to log in with email {string} and password {string}")
    public void theRegisteredUserTriesToLogInWithEmailAndPassword(String arg0, String arg1) {
    }

    @Then("the registered user should be successfully logged in")
    public void theRegisteredUserShouldBeSuccessfullyLoggedIn() {
    }

    @Then("the registered user should not be logged in")
    public void theRegisteredUserShouldNotBeLoggedIn() {
    }

    @Then("an error message {string} shall be raised")
    public void anErrorMessageShallBeRaised(String arg0) {
    }

    @When("the registered user tries to log in with username {string} and password {string}")
    public void theRegisteredUserTriesToLogInWithUsernameAndPassword(String arg0, String arg1) {
    }

    @Then("an error message {string} shall be raisedD")
    public void anErrorMessageShallBeRaisedD(String arg0) {
    }
}
