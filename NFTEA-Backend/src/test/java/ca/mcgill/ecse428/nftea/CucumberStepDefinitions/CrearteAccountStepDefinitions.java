package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.dao.UserAccountRepository;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CrearteAccountStepDefinitions {
    int errorCounter = 0;
    String error = "";
    UserAccount userAccount = null;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountService userAccountService;

    @After
    public void teardown(){
        int errorCounter = 0;
        String error = "";
        userAccount=null;
        userAccountRepository.deleteAll();
    }

    @Given("I write my personal information correctly")
    public void WroteAttributes() {
    }

    @When("I create an account")
    public void CreateAccount() throws Exception{
       try {
           userAccount = userAccountService.createUser("test", "test", "testw", "test@de.com", "deded498^#@");
       }
       catch (Exception e){
           error= e.getMessage();
           errorCounter++;
       }
    }
    @Then("My account should exist in the system")
    public void AccountInDb(){
        assertNotNull(userAccount);
        assertTrue(errorCounter==0);
        Long numberID = userAccount.getId();
        UserAccount Present=userAccountRepository.findUserAccountById(numberID);
        assertNotNull(Present);
    }


    @Given("I write my personal information incorrectly")
    public void WroteAttributesNoSuccess() {
    }

    @When("I create a bad account")
     public void CreateAccountWithNoSuccess() throws Exception{
        try {
            userAccount = userAccountService.createUser("", "test", "testw", "test@de.com", "deded498^#@");
        }
        catch (Exception e){
            error= e.getMessage();
            errorCounter++;
        }
    }
    @Then("My account should not exist in the system")
    public void NoAccountInDb(){
        assertNull(userAccount);
        assertTrue(errorCounter!=0);
        assertTrue(!error.equals(null));
    }



}
