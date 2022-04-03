package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.ListingService;
import ca.mcgill.ecse428.nftea.service.TransactionService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.datatable.DataTable;
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
public class discountStepDef {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    ListingService listingService;

    @Autowired
    private TransactionService transactionService;

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

         @Given("the user is logged in to an account with username {string} and password {string} and email {string} for the purpose of putting a discount$")
         public void the_user_is_logged_in_to_an_account_with_username_and_password_and_email_for_transaction2(String username, String password, String email) throws Exception{
                 userAccount = userAccountService.setUserOnline(username);}

    @Given("the following listings exist in the system")
    public void theFollowingListingsExistInTheSystemDiscount(io.cucumber.datatable.DataTable dataTable) {
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

    @Given("the following user accounts exist in the system")
    public void theFollowingUserAccountsExistInTheSystem(DataTable dataTable) throws Exception {
        List<List<String>> rows = dataTable.asLists();
        int i = 0;
        for (List<String> columns : rows){
            if (i == 0){
                i++;
            }
            else {
                String firstname = columns.get(0);
                String lastname = columns.get(1);
                String username = columns.get(2);
                String password = columns.get(3);
                String email = columns.get(4);
                userAccount =  userAccountService.createUser(firstname,lastname,username, email, password);

            }
        }
    }

    @When("the user tries to put a discount of {long} percent on the listing {string}")
    public void userputdiscount(long percent,String title){
        try {
            viewListings = listingService.getListingsByTitle(title);
           listingService.discountPrice(viewListings.get(0).getListingID(),percent) ;
        } catch (Exception e) {
            errorCounter++;
            error += e.getMessage();
        }
    }

    @Then("the discounted price will be {long}")
    public void seeIfpriceisDiscounted(long finalDiscountprice){
        assertEquals(finalDiscountprice,viewListings.get(0).getDiscountedPrice());
    }






}




