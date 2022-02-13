package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.LogOutService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertFalse;
@CucumberContextConfiguration
@SpringBootTest
public class LogOutStepDefinitions {




        UserAccount userAccount = null;

        int errorCounter = 0;
        String error = "";
        @Autowired
        private UserAccountService userAccountService;
        @Autowired
        private LogOutService logOutService;



        @After
        public void teardown(){
            int errorCounter = 0;
            String error = "";
             userAccount = null;

        }


        @Given("there is an online user")
        public void thereIsAnOnlineUser() throws Exception {
            userAccount =  userAccountService.createUser("test","test", "testw", "test@de.com", "deded498^#@");

        }


        @When("the online registered user tries to logout")
        public void theOnlineRegisteredUserTriesToLogout() {
            try{

                logOutService.LogOutAccount(userAccount.getId());
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


