package CucumberStepDefinitions.cucumber;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.LogOutService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import static org.junit.jupiter.api.Assertions.*;

public class LogoutStepDefinitions {

    UserAccount userAccount = null;
    UserAccountService userAccountService;
    LogOutService logOutService;
    int errorCounter = 0;
    String error = "";
    @After
    public void teardown(){
        UserAccount userAccount = null;
    }


    @Given("the user exists and is online in the system")
    public void theUserExistsAndIsOnlineInTheSystem() throws Exception {
        userAccount = userAccountService.createUser("test","test","test","test","test");

    }

    @When("the online registered user tries to logout")
    public void theOnlineRegisteredUserTriesToLogout() {
        try{
        logOutService.LogOutAccount(userAccount.getNumberID());
        }
        catch (Exception e){
            error = e.getMessage();
            errorCounter++;

        }

        }

    @Then("the registered user should be successfully logged out")
    public void theRegisteredUserShouldBeSuccessfullyLoggedOut() {
        assertFalse(userAccount.getIsLoggedIn());
    }
}
