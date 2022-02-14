package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class deleteAccountStepDefinitions {

    UserAccount userAccount;
    Long id;
    String real_pass;

    @Autowired
    UserAccountService userAccountService;

    @After
    public void teardown() {
        userAccount = null;
        id = null;
        real_pass = "";
        userAccountService.clear();
    }

    @Given("user {string} is currently logged in")
    public void loggedIn(String arg0) throws Exception {
        userAccount = userAccountService.setUserOnline(arg0);
        id = userAccount.getId();
        real_pass = userAccount.getPassword();
    }

    @Then("the account shall be deleted")
    public void theAccountShallBeDeleted() {
        assertNull(userAccountService.getAccount(id));
    }

    @Then("the account shall not be deleted")
    public void theAccountShallNotBeDeleted() {
        assertNotNull(userAccountService.getAccount(id));
    }

    @When("the user attempts to delete the account with the correct password {string}")
    public void theUserAttemptsToDeleteTheAccountWithTheCorrectPassword(String arg0) throws Exception {
        userAccountService.deleteUser(id, arg0);
    }

    @When("the user attempts to delete the account with the wrong password {string}")
    public void theUserAttemptsToDeleteTheAccountWithTheWrongPassword(String arg0) {
        try {
            userAccountService.deleteUser(id, arg0);
        } catch (Exception e) {
            String error = e.getMessage();
            assertTrue(error.contains("Invalid password for user"));
        }
    }
}
