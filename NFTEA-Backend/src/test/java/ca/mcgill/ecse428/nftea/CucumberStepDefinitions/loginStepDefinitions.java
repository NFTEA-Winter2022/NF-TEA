package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.LoginService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class loginStepDefinitions {

    int errorCounter = 0;
    String error = "";
    UserAccount userAccount = null;

    UserAccountService userAccountService;
    LoginService loginService;

    @After
    public void teardown(){
//        userAccountService.
        int errorCounter = 0;
        String error = "";
    }

    @Given("the following users exist in the system:")
    public void theFollowingUsersExistInTheSystem(io.cucumber.datatable.DataTable dataTable) throws Exception {
        List<List<String>> rows = dataTable.asLists();
        int i = 0;
        for (List<String> columns : rows){
            if (i == 0){
                i++;
            }
            else {
                String email = columns.get(0);
                String password = columns.get(1);
                userAccount =  userAccountService.createUser(null,null, null, email, password);

            }
        }
    }

    @When("the registered user tries to log in with email {string} and password {string}")
    public void theRegisteredUserTriesToLogInWithEmailAndPassword(String arg0, String arg1) {
        try{
            userAccount = loginService.loginUserAccount(arg0, arg1);
        }
        catch (Exception e){
            error = e.getMessage();
            errorCounter++;
        }
    }

    @Then("the registered user should be successfully logged in")
    public void theRegisteredUserShouldBeSuccessfullyLoggedIn() {
        assertTrue(userAccount.getIsLoggedIn());
    }

    @Then("the registered user should not be logged in")
    public void theRegisteredUserShouldNotBeLoggedIn() {
        assertFalse(userAccount.getIsLoggedIn());
    }

    @Then("an error message {string} shall be raised")
    public void anErrorMessageShallBeRaised(String arg0) {
        assertTrue(error.contains(arg0));
    }
    
}
