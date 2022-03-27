package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.Notification;
import ca.mcgill.ecse428.nftea.model.TradeOffer;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.ListingService;
import ca.mcgill.ecse428.nftea.service.NotificationService;
import ca.mcgill.ecse428.nftea.service.TradeOfferService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.catalina.User;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class BackendNotificationStepDefinitions {
    @Autowired
    TradeOfferService tradeService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    ListingService listingService;

    @Autowired
    NotificationService notificationService;

    UserAccount userAccount = null;
    Listing listing = null;
    TradeOffer tradeOffer = null;
    String error = "";
    List<Notification> notifications = new ArrayList<>();

    @After
    public void destroy(){
        notificationService.clear();
        tradeService.clear();
        listingService.clear();
        userAccountService.clear();
        error = "";
        tradeOffer = null;
        notifications = new ArrayList<>();
    }

    @Given("the following tradeOffers exist in the database:")
    public void the_following_tradeOffers_exist_in_the_database(io.cucumber.datatable.DataTable dataTable){
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

    @When("searching for notification with user {string}")
    public void searching_for_notification_with_user(String userEmail){
        try{
            UserAccount userAccount = userAccountService.getUserAccountByEmail(userEmail);
            notifications = notificationService.getNotifications(userAccount.getId());
        } catch (Exception e){
            error += e.getMessage();
        }
    }

    @Then("a list of notifications appears")
    public void a_list_of_notifications_should_appear(){
        assertEquals(1, notifications.size());
    }
}
