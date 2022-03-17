package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.ListingService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SearchListingsStepDefinitions {
    String error = "";
    int errorCounter = 0;
    Listing listing = null;
    List<Listing> listings = null;
    UserAccount userAccount = null;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    ListingService listingService;

    @After
    public void teardown(){
        listingService.clear();
        userAccountService.clear();
        error = "";
        listing = null;
        listings = null;
        userAccount = null;
    }

    @Given("the following listings exist in the system:")
    public void theFollowingListingsExistInTheSystem(io.cucumber.datatable.DataTable dataTable) throws Exception{
        List<List<String>> rows = dataTable.asLists();
        int i = 0;
        for (List<String> columns : rows){
            if (i == 0){
                i++;
            }
            else {
                Long listingID = Long.parseLong(columns.get(0));
                String title = columns.get(1);
                Long price  = Long.parseLong(columns.get(2));
                String nftLink = columns.get(3);
                UserAccount owner = userAccountService.getUserAccountByEmail(columns.get(4));
                listing = listingService.createInitialListing(title, price, nftLink, owner);
                listing.setListingID(listingID);
            }
        }
    }

    @Given("the following users exist:")
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

    @When("the user searches a listing with title {string}")
    public void the_user_searches_a_listing_with_title(String title){
        try{
            listings = listingService.getListingsWithTitle(title);
            System.out.println();
        }
        catch (Exception e){
            error = e.getMessage();
            errorCounter++;
        }
    }

    @Then("a list of listings should be return")
    public void a_list_of_listings_should_be_return(){
        assertEquals(1, listings.size());
    }

    @When("the user searches a listing with Owner {string}")
    public void the_user_searches_a_listing_with_Owner(String ownerEmail){
        try{
            UserAccount user = userAccountService.getUserAccountByEmail(ownerEmail);
            listings = listingService.getListingsWithOwner(user);
        }
        catch (Exception e){
            error = e.getMessage();
            errorCounter++;
        }
    }

    @Then("a list of listings with the same owner should be return")
    public void a_list_of_listings_with_the_same_owner_should_be_return(){
        assertEquals(2, listings.size());
    }

    @Then("an error shall be raised {string}")
    public void an_error_shall_be_raised(String message){
        assertTrue(error.contains(message));
    }

    @When("the user searches a listing containing the title {string}")
    public void the_user_searches_a_listing_containing_the_title(String title){
        try{
            listings = listingService.getListingsContainingTitle(title);
        }
        catch (Exception e){
            error = e.getMessage();
            errorCounter++;
        }
    }

    @Then("a list of listings with similar titles should be return")
    public void a_list_of_listings_with_similar_titles_should_be_return(){
        assertEquals(4, listings.size());
    }

    @When("the user searches a listing with NFT link {string}")
    public void the_user_searches_a_listing_with_NFT_link(String link){
        try{
            listing = listingService.getListingWithNFTLink(link);
        }
        catch (Exception e){
            error = e.getMessage();
            errorCounter++;
        }
    }

    @Then("a listing should be return with NFT link {string}")
    public void a_listing_should_be_return(String link){
        assertEquals(listing.getNftLink(), link);
    }


}
