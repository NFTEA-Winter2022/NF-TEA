package CucumberStepDefinitions.cucumber;

import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.*;

import java.util.List;

import static org.junit.Assert.*;

public class changeUsernameStepDefinitions {

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
        userAccountService.clear();
        userAccount = null;
    }

    //might need to remove it
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

    @When("the user updates his account with a new username {string}")
    public void theUserUpdatesHisAccountWithANewUsername(String arg0) {
        try {
            userAccount = userAccountService.changeUserInfo(arg0,pass,pass, email_l);
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
        assertEquals(user, userAccount.getUsername());
    }

    //might need to remove it
    @And("an error message {string} shall be raised")
    public void anErrorMessageShallBeRaised(String arg0) {
        assertTrue(error.contains(arg0));
    }
}
