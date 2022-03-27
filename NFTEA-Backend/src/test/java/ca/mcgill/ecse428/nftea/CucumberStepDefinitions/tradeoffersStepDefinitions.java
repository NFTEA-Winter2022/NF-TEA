package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.dao.TradeOfferRepository;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.TradeOffer;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.ListingService;
import ca.mcgill.ecse428.nftea.service.TradeOfferService;
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
public class tradeoffersStepDefinitions {
    
    @Autowired
    TradeOfferService tradeService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    ListingService listingService;

    int errorCounter = 0;
    String error = "";
    UserAccount userAccount = null;
    Listing listing = null;
    ArrayList<Listing> listings = new ArrayList<>();
    ArrayList<TradeOffer> tradeoffers = new ArrayList<>();
    ArrayList<Listing> viewListings = new ArrayList<>();
    static long NON_EXISTING_LISTING = 38279423L;

    @After
    public void teardown(){
        errorCounter = 0;
        error = "";
        userAccount = null;
        listing = null;
        tradeoffers = new ArrayList<>();
        listings = new ArrayList<>();
        viewListings = new ArrayList<>();
        listingService.clear();
        tradeService.clear();
        userAccountService.clear();
    }

//    @Given("the following listings exist in the system")
//    public void theFollowingListingsExistInTheDB(io.cucumber.datatable.DataTable dataTable) {
//        List<List<String>> rows = dataTable.asLists();
//        int i = 0;
//        for (List<String> columns : rows){
//            if (i == 0){
//                i++;
//            }
//            else {
//                String title = columns.get(0);
//                long price = Long.valueOf(columns.get(1));
//                String nftLink = columns.get(2);
//                String email = columns.get(3);
//                UserAccount owner = userAccountService.getUserAccountByEmail(email);
//                Listing l = listingService.createInitialListing(title, price, nftLink, owner);
//                listings.add(l);
//            }
//        }
//    }
//    @Given("the user is logged in to an account with username {string} and password {string} and email {string} to delete listing")
//    public void theUserIsLoggedInToAnAccountWithUsernameAndPasswordAndEmailToCreateTradeOff(String arg0, String arg1, String arg2) throws Exception {
//        userAccount = userAccountService.setUserOnline(arg0);
//    }

    @When("the user with id {string} tries to create a trade off to user {string} with a listing id of {string} with a price {string}")
    public void userTriestoCreateTradeOff(String senderID, String receiverID, String listingID, String price){
        try{
            long realSenderID = Long.valueOf(senderID);
            long realReceiverID = Long.valueOf(receiverID);
            long realListingID = Long.valueOf(listingID);
            long realPrice = Long.valueOf(price);
            TradeOffer goodTrade = tradeService.createTradeOffer(realSenderID, realReceiverID, realListingID, realPrice);
            tradeoffers.add(goodTrade);
        }
        catch (Exception e){
            error+=e.getMessage();
        }
    }

    @Then("the trade off shall be successfully created")
    public void createdTradeOfferSuccessfully(){
        assertEquals(4, tradeoffers.size());
    }
}
