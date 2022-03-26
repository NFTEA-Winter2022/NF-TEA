package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;


import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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
    List<UserAccount> userAccounts = new ArrayList<>();

    @After
    public void teardown(){
        errorCounter = 0;
        error = "";
        userAccount = null;
        userAccounts = new ArrayList<>();
        userAccountService.clear();
    }

    @Given("the following user accounts exist in the system for search user account")
    public void theFollowingUserAccountsExistInTheSystem(DataTable dataTable) throws Exception {
        List<List<String>> rows = dataTable.asLists();
        int i = 0;
        for (List<String> columns : rows){
            if (i == 0){
                i++;
            }
            else {

                String firstname = columns.get(0);
                String lastname = columns.get(1);
                String username = columns.get(2);
                String password = columns.get(3);
                String email = columns.get(4);
                UserAccount u = userAccountService.createUser(firstname,lastname,username, email, password);
                userAccounts.add(u);
            }
        }
    }

    @When("the user search for the user {string} using the numberID")
    public void the_user_search_for_the_user_with_the_number_id(String username) {
        try {
            Long numberID = null;
            for(UserAccount u : userAccounts) {
                if(u.getUsername().equals(username)) {
                    numberID = u.getId();
                }
            }
            userAccount = userAccountService.searchAccountByUserId(numberID);
        } catch (Exception e) {
            error += e.getMessage();
        }
    }

    @When("the user search for the user with the numberID {int}")
    public void the_user_search_for_the_user_with_the_number_id(Integer numberID) {
        try {
            userAccount = userAccountService.searchAccountByUserId(Long.valueOf(numberID));
        } catch (Exception e) {
            error += e.getMessage();
        }
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
        try {
            userAccount = userAccountService.searchAccountByUserId(null);
        } catch (Exception e) {
            error += e.getMessage();
        }
    }
    @Then("an error message {string} shall be raised for search user account")
    public void an_error_message_shall_be_raised_for_search_user_account(String errorMsg) {
        assertTrue(error.contains(errorMsg));
    }

    @When("the user search for the user {string}")
    public void the_user_search_for_the_user(String username) {
        try {
            userAccount = userAccountService.searchAccountByUsername(username);
        } catch (Exception e) {
            error += e.getMessage();
        }
    }

}
