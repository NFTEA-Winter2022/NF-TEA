package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.LoginService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class loginStepDefinitions {

    private LocalDateTime currentDateTime;

    int errorCounter = 0;
    String error = "";
    UserAccount userAccount = null;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    LoginService loginService;

    @After
    public void teardown(){
        userAccountService.clear();
        int errorCounter = 0;
        String error = "";
        userAccount = null;
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
                String firstName = columns.get(0);
                String lastName = columns.get(1);
                String username = columns.get(2);
                String email = columns.get(3);
                String password = columns.get(4);
                userAccount =  userAccountService.createUser(firstName,lastName, username, email, password);

            }
        }
    }

    @Given("the current date and time {string}")
    public void the_current_date_and_time(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        currentDateTime = LocalDateTime.parse(dateTimeStr, formatter);
    }

    @Given("the registered user is not logged in with {string}")
    public void the_registered_user_is_not_logged_in_with(String email) {
        userAccount = userAccountService.setUserOnline(email, false);
    }

    @Given("the registered user is logged in with {string}")
    public void the_registered_user_is_logged_in_with(String email) {
        userAccount = userAccountService.setUserOnline(email, true);
    }

//    @Given("{string} has {int} attempt")
//    public void the_registered_user_is_logged_in_with(String email, int attempts) {
//        UserAccount user = userAccountService.getUserAccountByEmail(email);
//        userAccount.setLoginAttempts(attempts);
//        userAccountService.saveAccount(user);
//    }


    @When("the registered user tries to log in with email {string} and password {string}")
    public void theRegisteredUserTriesToLogInWithEmailAndPassword(String email, String password) {
        try{
            userAccount = loginService.loginUserAccount(email, password);
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

//    @Then("the registered user should not be logged in")
//    public void theRegisteredUserShouldNotBeLoggedIn() {
//        assertFalse(userAccount.getIsLoggedIn());
//    }

//    @Then("an error message {string} shall be raised")
//    public void anErrorMessageShallBeRaised(String errorMsg) {
//        assertTrue(error.contains(errorMsg));
//    }

    @Then("{string} should have {int} attempts")
    public void should_have_attempts(String email, int attempts) {
//        UserAccount user = userAccountService.getUserAccountByEmail(email);
        assertEquals(attempts, userAccount.getLoginAttempts());
    }

    @Then("{string}'s most recent attempt should be at {string}")
    public void most_recent_attempt_should_be_at(String email, String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

//        UserAccount user = userAccountService.getUserAccountByEmail(email);
        assertEquals(dateTime, userAccount.getLastAttempt());
    }

    @Given("{string} has {int} attempts")
    public void hasAttempts(String email, int attempts) {
        UserAccount user = userAccountService.getUserAccountByEmail(email);
        user.setLoginAttempts(attempts);
        userAccountService.saveAccount(user);
    }

    @When("the registered user tries to log in with email {string}, password {string}")
    public void theRegisteredUserTriesToLogInWithEmailPassword(String arg0, String arg1) {
        try{
            loginService.loginUserAccount(arg0, arg1);
        }
        catch (Exception e){
            error = e.getMessage();
            errorCounter++;
        }

    }

    @Then("{string} should have {string} attempts")
    public void shouldHaveAttempts(String arg0, String arg1) {
        UserAccount user = userAccountService.getUserAccountByEmail(arg0);
        assertEquals(Integer.parseInt(arg1), user.getLoginAttempts());
    }

    @Given("{string} has {int} attempts and the last attempt {string}")
    public void hasAttemptsAndTheLastAttempt(String arg0, int arg1, String arg2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(arg2, formatter);

        UserAccount user = userAccountService.getUserAccountByEmail(arg0);
        user.setLastAttempt(dateTime);
        user.setLoginAttempts(arg1);
        userAccountService.saveAccount(user);

    }

    @Then("an error message shall be raised {string}")
    public void anErrorMessageShallBeRaised(String arg0) {
        assertTrue(error.contains(arg0));
    }

    @Given("{string} has this amount of attempts {string}")
    public void hasThisAmountOfAttempts(String arg0, String arg1) {
        UserAccount user = userAccountService.getUserAccountByEmail(arg0);
        user.setLoginAttempts(Integer.parseInt(arg1));
        userAccountService.saveAccount(user);
    }

    @Then("the registered user should not be logged in with {string}")
    public void theRegisteredUserShouldNotBeLoggedInWith(String arg0) {
        UserAccount userAccount = userAccountService.getUserAccountByEmail(arg0);
        assertFalse(userAccount.getIsLoggedIn());
    }

    @Then("the registered user should be successfully logged in {string}")
    public void theRegisteredUserShouldBeSuccessfullyLoggedIn(String arg0) {
        UserAccount userAccount = userAccountService.getUserAccountByEmail(arg0);
        assertTrue(userAccount.getIsLoggedIn());
    }

    @Then("the registered user should not be logged in")
    public void theRegisteredUserShouldNotBeLoggedIn() {
    }
}
