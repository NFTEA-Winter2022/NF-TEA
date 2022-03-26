package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.TradeOffer;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.ListingService;
import ca.mcgill.ecse428.nftea.service.TradeOfferService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class AcceptTradeOffersStepDefinitions {
    @Autowired
    TradeOfferService tradeService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    ListingService listingService;

    int errorCounter = 0;
    String error = "";
    TradeOffer tradeOffer = null;


    @After
    public void teardown(){
        errorCounter = 0;
        error = "";
        tradeOffer = null;
        listingService.clear();
        tradeService.clear();
        userAccountService.clear();
    }

    @Given("the following tradeOffers exist in the system:")
    public void the_following_tradeOffers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable){
        List<List<String>> rows = dataTable.asLists();
        int i = 0;
        for (List<String> columns : rows){
            if (i == 0){
                i++;
            }
            else {
                String emailSender = columns.get(0);
                Long senderID = userAccountService.getUserAccountByEmail(emailSender).getId();
                String emailReceiver = columns.get(1);
                Long receiverID = userAccountService.getUserAccountByEmail(emailReceiver).getId();
                String listingLink = columns.get(2);
                Long listingID = listingService.getListingsByNFTLink(listingLink).get(0).getListingID();
                Long price = Long.parseLong(columns.get(3));
                tradeOffer = tradeService.createTradeOffer(senderID, receiverID, listingID, price);

            }
        }
    }

    @Given("a tradeOffer is OnGoing")
    public void a_tradeOffer_is_OnGoing(){
        tradeOffer.setOnGoing(true);
        tradeOffer.setAccepted(false);
        tradeOffer.setDeclined(false);
    }

    @Given("a tradeOffer is declined")
    public void a_tradeOffer_is_declined(){
        tradeOffer.setOnGoing(false);
        tradeOffer.setAccepted(false);
        tradeOffer.setDeclined(true);
    }

    @Given("a tradeOffer is accepted")
    public void a_tradeOffer_is_accepted(){
        tradeOffer.setOnGoing(false);
        tradeOffer.setAccepted(true);
        tradeOffer.setDeclined(false);
    }

    @When("a tradeOffer tries to get accepted")
    public void a_tradeOffer_tries_to_get_accepted(){
        try{
            tradeService.acceptTradeOffer(tradeOffer.getId());
        }
        catch (Exception e){
            error += e.getMessage();
        }
    }

    @Then("the tradeOffer is Accepted")
    public void the_tradeOffer_is_Accepted(){
        assertTrue(tradeOffer.isAccepted());
    }





}
