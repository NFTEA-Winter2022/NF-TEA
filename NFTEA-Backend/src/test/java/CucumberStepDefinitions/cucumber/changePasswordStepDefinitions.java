package CucumberStepDefinitions.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.*;

import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.UserAccountService;

import io.cucumber.datatable.DataTable;

import java.util.List;

import static org.junit.Assert.*;

public class changePasswordStepDefinitions {

    int errorCounter = 0;
    String error = "";
    UserAccount userAccount = null;

    UserAccountService userAccountService;

    String user ="";
    String pass = "";
    String email_l = "";

    @After
    public void teardown(){
        errorCounter = 0;
        error = "";
        user ="";
        pass = "";
        email_l = "";
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
                userAccount =  userAccountService.createUser(firstname,lastname,username,password, email);
            }
        }
    }

    @Given("the user is logged in to an account with username {string} and password {string} and email {string}")
    public void theUserIsLoggedInToAnAccountWithUsernameAndPasswordAndEmail(String arg0, String arg1, String arg2) throws Exception {
        userAccount = userAccountService.setUserOnline(arg0);
        user = arg0;
        pass = arg1;
        email_l = arg2;
    }

    @When("the user updates his account with {string} and confirms password {string}")
    public void theUserUpdatesHisAccountWithAndConfirmsPassword(String arg0, String arg1) {
        try {
            userAccount = userAccountService.changeUserInfo(userAccount.getUsername(),arg0,arg1, email_l);
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

    @But("the new password {string} and confirm password {string} do not match")
    public void theNewPasswordAndConfirmPasswordDoNotMatch(String arg0, String arg1) {
        assertNotEquals(arg0,arg1);
    }

    @And("an error message {string} shall be raised")
    public void anErrorMessageShallBeRaised(String arg0) {
        assertTrue(error.contains(arg0));
    }
}
