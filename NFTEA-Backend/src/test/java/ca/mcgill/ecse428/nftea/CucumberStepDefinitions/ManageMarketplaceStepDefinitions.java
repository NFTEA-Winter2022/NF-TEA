package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.ListingService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class ManageMarketplaceStepDefinitions {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    ListingService listingService;

    ArrayList<Listing> listings;
    ArrayList<UserAccount> userAccounts;
    String error = "";


    @After
    public void teardown(){
        error = "";
        listings = new ArrayList<>();
        userAccounts = new ArrayList<>();
        listingService.clear();
        userAccountService.clear();
    }
    @When("an admin checks all the listings")
    public void anAdminChecksAllTheListings() {
        try{
            listings = listingService.getListings();
        } catch (Exception e){
            error += e.getMessage();
        }
    }

    @Then("a list of listings should show")
    public void aListOfListingsShouldShow(){
        assertEquals(2, listings.size());
    }

    @When("an admin checks all the users")
    public void anAdminChecksAllTheUsers(){
        try{
            userAccounts = userAccountService.getUserAccounts();
        } catch (Exception e){
            error += e.getMessage();
        }
    }

    @Then("a list of users should show")
    public void aListOfUsersShouldShow(){
        assertEquals(2, userAccounts.size());
    }

    @When("an admin deletes an account {string}")
    public void anAdminDeletesAnAccount(String accountEmail){
        try {
            Long userAccountID = userAccountService.getUserAccountByEmail(accountEmail).getId();
            String password = userAccountService.getUserAccountByEmail(accountEmail).getPassword();
            userAccountService.deleteUser(userAccountID,password);
        } catch (Exception e){
            error += e.getMessage();
        }
    }

    @Then("the user {string} would be deleted from the database")
    public void theUserWouldBeDeletedFromTheDatabase(String accountEmail){
        UserAccount userAccount = userAccountService.getUserAccountByEmail(accountEmail);
        assertNull(userAccount);
    }

    @When("the listings of the user {string} will be empty")
    public void theListingsOfTheUserWillBeEmpty(String accountEmail){
        ArrayList<Listing> listings = listingService.getListingsWithOwner(userAccountService.getUserAccountByEmail(accountEmail));
        assertEquals(0, listings.size());
    }
}
