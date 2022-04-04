package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.model.FavouriteListing;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.FavouriteListingService;
import ca.mcgill.ecse428.nftea.service.ListingService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class WatchlistedFavouritesStepDefinitions {
    @Autowired
    UserAccountService userAccountService;

    @Autowired
    ListingService listingService;

    @Autowired
    FavouriteListingService favouriteListingService;

    String error = "";
    List<FavouriteListing> favouriteListings = new ArrayList<>();

    @After
    public void teardown(){
        listingService.clear();
        userAccountService.clear();
        error = "";
        favouriteListings = new ArrayList<>();
    }

    @When("a user {string} creates a favourite listing {string}")
    public void aUserCreatesAFavouriteListing(String arg1, String arg2) {
        try{
            UserAccount userAccount = userAccountService.getUserAccountByEmail(arg1);
            Listing listing = listingService.getListingsByNFTLink(arg2).get(0);
            favouriteListings.add(favouriteListingService.createFavourite(userAccount, listing));
        } catch (Exception e){
            error += e.getMessage();
        }
    }

    @Then("the listing {string} will be favoured in the user {string}")
    public void theListingWillBeFavouredInTheUser(String arg1, String arg2) {
        Long userID = userAccountService.getUserAccountByEmail(arg2).getId();
        assertEquals(1, favouriteListingService.getALLFavouriteListings(userID).size());
    }

    @When("deleting a favourite listing {string}")
    public void deletingAFavouriteListing(String arg1) {
        try{
            Listing listing = listingService.getListingsByNFTLink(arg1).get(0);
            favouriteListings.add(favouriteListingService.deleteFavourite(listing.getListingID()));
        } catch (Exception e){
            error += e.getMessage();
        }
    }

    @Then("the listing of user {string} will not be favoured")
    public void theListingOfUserWillNotBeFavoured(String arg1) {
        UserAccount userAccount = userAccountService.getUserAccountByEmail(arg1);
        assertEquals(0, favouriteListingService.getALLFavouriteListings(userAccount.getId()).size());
    }

    @When("a user {string} deletes a favourite listing {string}")
    public void aUserDeletesAFavouriteListing(String arg1, String arg2) {
        try{
            Listing listing = listingService.getListingsByNFTLink(arg1).get(0);
            UserAccount userAccount = userAccountService.getUserAccountByEmail(arg1);
            favouriteListings.add(favouriteListingService.deleteByUserAndListing(userAccount.getId(), listing.getListingID()));
        } catch (Exception e) {
            error += e.getMessage();
        }
    }

    @Then("the listing {string} will not be favoured in the user {string}")
    public void theListingWillNotBeFavouredInTheUser(String arg1, String arg2) {
        Long userID = userAccountService.getUserAccountByEmail(arg2).getId();
        Long listingID = listingService.getListingsByNFTLink(arg1).get(0).getListingID();

        assertNull(favouriteListingService.getFavouriteByUserAndListing(userID, listingID));
    }



}
