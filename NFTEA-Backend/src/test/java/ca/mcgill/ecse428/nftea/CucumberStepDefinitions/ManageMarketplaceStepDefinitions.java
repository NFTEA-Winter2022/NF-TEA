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
}
