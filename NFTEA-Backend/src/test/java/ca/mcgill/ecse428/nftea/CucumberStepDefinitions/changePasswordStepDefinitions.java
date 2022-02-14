package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.*;

import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.UserAccountService;

import io.cucumber.datatable.DataTable;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;

//@CucumberContextConfiguration
@SpringBootTest
public class changePasswordStepDefinitions {

    int errorCounter = 0;
    String error = "";
    UserAccount userAccount = null;
    @Autowired
    UserAccountService userAccountService;

    String user ="";
    String real_pass = "";

    Long idTest;

    @After
    public void teardown(){
        errorCounter = 0;
        error = "";
        user ="";
        real_pass = "";
        userAccountService.clear();
    }

    @Given("the following user accounts exist in the system")
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
                userAccount =  userAccountService.createUser(firstname,lastname,username, email, password);

            }
        }
    }

    @Given("the user is logged in to an account with username {string} and password {string} and email {string}")
    public void theUserIsLoggedInToAnAccountWithUsernameAndPasswordAndEmail(String arg0, String arg1, String arg2) throws Exception {
        userAccount = userAccountService.setUserOnline(arg0);
        idTest = userAccount.getId();
        real_pass = arg1;
    }

    @When("the user updates his account with {string} and confirms password {string}")
    public void theUserUpdatesHisAccountWithAndConfirmsPassword(String arg0, String arg1) {
        try {
            userAccount = userAccountService.setNewPassword(idTest,arg1, arg0);
        }
        catch (Exception e) {
            error = e.getMessage();
            errorCounter++;
        }
    }

    @Then("the account shall have password {string}")
    public void theAccountShallHavePassword(String arg0) {
        assertEquals(userAccount.getPassword(),arg0);
    }

    @But("the password {string} and confirm password {string} do not match")
    public void thePasswordAndConfirmPasswordDoNotMatch(String arg0, String arg1) {
        assertNotEquals(arg0,arg1);
    }

    @And("an error message {string} shall be raised")
    public void anErrorMessageShallBeRaised(String arg0) {
        assertTrue(error.contains(arg0));
    }

    @When("the user updates his account with a new username {string}")
    public void theUserUpdatesHisAccountWithANewUsername(String arg0) {
        try {
            userAccount = userAccountService.setNewUsername(idTest,arg0);
            user = userAccount.getUsername();
        }
        catch (Exception e) {
            error = e.getMessage();
            errorCounter++;
        }
    }

    @Then("the account shall have username {string}")
    public void theAccountShallHaveUsername(String arg0) {
        assertEquals(userAccount.getUsername(),arg0);
    }

    @But("the new username {string} is invalid")
    public void theNewUsernameIsInvalid(String arg0) {
        assertEquals(0, arg0.length());
        assertEquals(user, arg0);
    }
}
