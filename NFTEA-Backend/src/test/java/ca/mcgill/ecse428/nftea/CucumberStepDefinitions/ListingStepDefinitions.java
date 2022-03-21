package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.ListingService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ListingStepDefinitions {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    ListingService listingService;

    int errorCounter = 0;
    String error = "";
    UserAccount userAccount = null;
    Listing listing = null;
    ArrayList<Listing> listings = new ArrayList<>();
    ArrayList<Listing> viewListings = new ArrayList<>();
    static long NON_EXISTING_LISTING = 38279423L;

    @After
    public void teardown(){
        errorCounter = 0;
        error = "";
        userAccount = null;
        listing = null;
        listings = new ArrayList<>();
        viewListings = new ArrayList<>();
        listingService.clear();
        userAccountService.clear();
    }

    @Given("the following listings exist in the system")
    public void theFollowingListingsExistInTheSystem(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists();
        int i = 0;
        for (List<String> columns : rows){
            if (i == 0){
                i++;
            }
            else {
                String title = columns.get(0);
                long price = Long.valueOf(columns.get(1));
                String nftLink = columns.get(2);
                String email = columns.get(3);
                UserAccount owner = userAccountService.getUserAccountByEmail(email);
                Listing l = listingService.createInitialListing(title, price, nftLink, owner);
                listings.add(l);
            }
        }
    }

    @Given("the user is logged in to an account with username {string} and password {string} and email {string} to delete listing")
    public void theUserIsLoggedInToAnAccountWithUsernameAndPasswordAndEmailToDeleteListing(String arg0, String arg1, String arg2) throws Exception {
        userAccount = userAccountService.setUserOnline(arg0);
    }

    @When("the user tries to create a listing with title {string} and price {string} and email {string}")
    public void userCreatesListingNoNFTLINK(String title, String price, String email) {
        try {
            long priceL = Long.valueOf(price);
            UserAccount owner = userAccountService.getUserAccountByEmail(email);
            Listing l = listingService.createInitialListing(title, priceL, null, owner);
        } catch (Exception e) {
            error += e.getMessage();
        }
    }

    @Then("no listing shall be created")
    public void noListingCreated() {
        assertEquals(2, listings.size());
    }

    @And("the error message {string} shall be raised")
    public void errorRaised(String errorMsg) {
        assertEquals(errorMsg, error);
    }

    @When("the user tries to create a listing with title {string} and price {string} and nftLink {string}")
    public void userCreatesListingNoUser(String title, String price, String nft) {
        try {
            long priceL = Long.valueOf(price);
            Listing l = listingService.createInitialListing(title, priceL, nft, null);
        } catch (Exception e) {
            error += e.getMessage();
        }
    }

    @When("the user tries to create a listing with title {string} and price {string} and nftLink {string} and email {string}")
    public void userTriesToCreateListing(String title, String price, String nftLink, String email) {
        try {
            long priceL = Long.valueOf(price);
            UserAccount owner = userAccountService.getUserAccountByEmail(email);
            Listing l = listingService.createInitialListing(title, priceL, nftLink, owner);
            listings.add(l);
        } catch (Exception e) {
            errorCounter++;
            error += e.getMessage();
        }
    }

    @Then("the listing shall be created successfully")
    public void createdListingSuccessful() {
        assertEquals(3, listings.size());
    }

    @When("the user tries to delete the listing {string}")
    public void the_user_tries_to_delete_the_listing(String string) {
        try {
            //TODO : call service method
        } catch (Exception e) {
            errorCounter++;
            error += e.getMessage();
        }
    }
    @Then("the listing shall be deleted successfully")
    public void the_listing_shall_be_deleted_successfully() {
        //TODO: uncomment line
        assertEquals(1, listings.size());
    }
    @Then("no error message shall be raised for listings")
    public void no_error_message_shall_be_raised_for_listings() {
        assertEquals(0, errorCounter);
    }

    @And("an error message {string} shall be raised for listings")
    public void anErrorMessageShallBeRaisedForListings(String arg0) {
        assertTrue(error.contains(arg0));
    }

    @Given("the user is logged in to an account with username {string} and password {string} and email {string} to edit listing")
    public void theUserIsLoggedInToAnAccountWithUsernameAndPasswordAndEmailToEditListing(String arg0, String arg1, String arg2) throws Exception {
        userAccount = userAccountService.setUserOnline(arg0);
    }

    @When("the user tries to edit listing {string}'s title to {string}")
    public void the_user_tries_to_edit_listing_s_title_to(String title, String newTitle) {
        try {
            for(Listing l : listings) {
                if(l.getTitle().equals(title)) {
                    listing = l;
                }
            }
            if(listing == null) {
                listing = listingService.editTitle(NON_EXISTING_LISTING, newTitle);
            } else {
                listing = listingService.editTitle(listing.getListingID(), newTitle);
            }
        } catch (Exception e) {
            errorCounter++;
            error += e.getMessage();
        }

    }
    @Then("the listing's name shall be {string}")
    public void the_listing_s_name_shall_be(String title) {
        assertEquals(title, listing.getTitle());
    }
    @When("the user tries to edit listing {string}'s price to {long}")
    public void the_user_tries_to_edit_listing_s_price_to(String title, long price) {
        try {
            for(Listing l : listings) {
                if(l.getTitle().equals(title)) {
                    listing = l;
                }
            }
            if(listing == null) {
                listing = listingService.editPrice(NON_EXISTING_LISTING, price);
            } else {
                listing = listingService.editPrice(listing.getListingID(), price);
            }
        } catch (Exception e) {
            errorCounter++;
            error += e.getMessage();
        }
    }
    @Then("the listing's price shall be {long}")
    public void the_listing_s_price_shall_be(long price) {
        assertEquals(price, listing.getPrice());
    }

    @When("the user tries to edit listing {string}'s NFT link to {string}")
    public void the_user_tries_to_edit_listing_s_nft_link_to(String title, String nftLink) {
        try {
            for(Listing l : listings) {
                if(l.getTitle().equals(title)) {
                    listing = l;
                }
            }
            if(listing == null) {
                listing = listingService.editNftLink(NON_EXISTING_LISTING, nftLink);
            } else {
                listing = listingService.editNftLink(listing.getListingID(), nftLink);
            }
        } catch (Exception e) {
            errorCounter++;
            error += e.getMessage();
        }

    }
    @Then("the listing's NFT link shall be {string}")
    public void the_listing_s_nft_link_shall_be(String nftLink) {
        assertEquals(nftLink, listing.getNftLink());
    }

    @Given("the user is logged in to an account with username {string} and password {string} and email {string} to view listings")
    public void theUserIsLoggedInToAnAccountWithUsernameAndPasswordAndEmailToViewListings(String arg0, String arg1, String arg2) throws Exception {
        userAccount = userAccountService.setUserOnline(arg0);
    }

    @When("the user tries to to view listings")
    public void the_user_tries_to_to_view_listings() {
        try {
            viewListings = listingService.getListings();
        } catch (Exception e) {
            errorCounter++;
            error += e.getMessage();
        }
    }

    @When("the user tries to to view listings by title {string}")
    public void the_user_tries_to_to_view_listings_by_title(String title) {
        try {
            viewListings = listingService.getListingsByTitle(title);
        } catch (Exception e) {
            errorCounter++;
            error += e.getMessage();
        }
    }

    @When("the user tries to to view listings by price {long}")
    public void the_user_tries_to_to_view_listings_by_price(long price) {
        try {
            viewListings = listingService.getListingsByPrice(price);
        } catch (Exception e) {
            errorCounter++;
            error += e.getMessage();
        }
    }

    @When("the user tries to to view listings by NFT link {string}")
    public void the_user_tries_to_to_view_listings_by_nft_link(String nftLink) {
        try {
            viewListings = listingService.getListingsByNFTLink(nftLink);
        } catch (Exception e) {
            errorCounter++;
            error += e.getMessage();
        }
    }

    @Then("the total listings shall be {int}")
    public void the_total_listings_shall_be(Integer size) {
        assertEquals(size, viewListings.size());
    }
    @Then("the listings provided shall contain {string}")
    public void the_listings_provided_shall_contain(String title) {
        boolean found = false;
        for(Listing l : viewListings) {
            if(l.getTitle().equals(title)) {
                found = true;
            }
        }
        assertTrue(found);
    }
}
