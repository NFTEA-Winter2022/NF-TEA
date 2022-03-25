package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;


import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SearchUserStepDefinitions {

    @Autowired
    UserAccountService userAccountService;

    int errorCounter = 0;
    String error = "";
    UserAccount userAccount = null;

    @When("the user search for the user {string} with the numberID")
    public void the_user_search_for_the_user_with_the_number_id(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user search for the user with the numberID {int}")
    public void the_user_search_for_the_user_with_the_number_id(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user account returned shall have the username {string}")
    public void the_user_account_returned_shall_have_the_username(String expected) {
        assertEquals(expected, userAccount.getUsername());
    }

    @Then("no error message shall be raised for search user account")
    public void no_error_message_shall_be_raised_for_search_user_account() {
        assertEquals(0, errorCounter);
    }

    @Then("no user account shall be returned")
    public void no_user_account_shall_be_returned() {
        assertNull(userAccount);
    }

    @When("the user search for the user with a null numberID")
    public void the_user_search_for_the_user_with_a_null_number_id() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("an error message {string} shall be raised for search user account")
    public void an_error_message_shall_be_raised_for_search_user_account(String errorMsg) {
        assertTrue(error.contains(errorMsg));
    }

    @When("the user search for the user {string}")
    public void the_user_search_for_the_user(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
