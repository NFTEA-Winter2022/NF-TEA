package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.dao.TransactionRepository;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.Transaction;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.ListingService;
import ca.mcgill.ecse428.nftea.service.TransactionService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransactionStepDefinitions {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    ListingService listingService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionRepository transactionRepository;


    int errorCounter = 0;
    String error = "";
    UserAccount userAccount = null;
    Transaction transaction = null;
    List<Transaction> toDisplay = new ArrayList<>();
    int numberOfTransactions = 0;
    static Long NON_EXISTING_ID = 3289L;

    @After
    public void teardown(){
        errorCounter = 0;
        error = "";
        numberOfTransactions = 0;
        userAccount = null;
        transaction = null;
        toDisplay = new ArrayList<>();
        transactionRepository.deleteAll();
        listingService.clear();
        userAccountService.clear();

    }

    @Given("the user is logged in to an account with username {string} and password {string} and email {string} for transaction")
    public void the_user_is_logged_in_to_an_account_with_username_and_password_and_email_for_transaction(String username, String password, String email) {
        try {
            userAccount = userAccountService.setUserOnline(username);
            numberOfTransactions = transactionService.getTransactions().size();
        } catch (Exception e) {
            this.errorCounter++;
            this.error += e.getMessage();
        }
    }

    @When("the user tries to buy the nft listed under {string}")
    public void the_user_tries_to_buy_the_nft_listed_under(String title) {
        List<Listing> listings = listingService.getListings();
        Long listingId = null;
        for(Listing l : listings) {
            if(l.getTitle().equals(title)) {
                listingId = l.getListingID();
            }
        }
        if(listingId == null) {
            listingId = NON_EXISTING_ID;
        }
        try {
            transaction = transactionService.createTransaction(userAccount.getId(), listingId);
        } catch (IllegalArgumentException e) {
            errorCounter++;
            error += e.getMessage();
        }
    }

    @Then("a transaction receipt for the listing {string} shall be generated")
    public void a_transaction_receipt_for_the_listing_shall_be_generated(String title) {
        List<Transaction> transactions = transactionService.getTransactions();
        Transaction transaction = null;
        for(Transaction t: transactions) {
            if(t.getTitle().equals(title)) {
                transaction = t;
            }
        }
        assertNotNull(transaction);
    }

    @Then("the listing {string} shall no longer exist")
    public void the_listing_shall_no_longer_exist(String title) {
        List<Listing> listings = listingService.getListings();
        Listing listing = null;
        for(Listing l : listings) {
            if(l.getTitle().equals(title)) {
                listing = l;
            }
        }
        assertNull(listing);
    }

    @Then("no error message shall be raised for transaction")
    public void no_error_message_shall_be_raised_for_transaction() {
        assertEquals(0, errorCounter);
    }

    @When("the user tries to view transaction receipt for nfts he bought")
    public void the_user_tries_to_view_transaction_receipt_for_nfts_he_bought() {
        try {
            toDisplay = transactionService.getTransactionsByBuyer(userAccount.getId());
        } catch (IllegalArgumentException e) {
            errorCounter++;
            error += e.getMessage();
        }
    }
    @Then("the list of transaction shall have length {int}")
    public void the_list_of_transaction_shall_have_length(Integer length) {
        assertEquals(length, toDisplay.size());
    }
    @Then("the list shall contain an entry for the listing {string}")
    public void the_list_shall_contain_an_entry_for_the_listing(String title) {
        Transaction transaction = null;
        for(Transaction t : toDisplay) {
            if(t.getTitle().equals(title)) {
                transaction = t;
            }
        }
        assertNotNull(transaction);
    }

    @Given("the following transactions exist in the system")
    public void the_following_transactions_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists();
        int i = 0;
        for (List<String> columns : rows){
            if (i == 0){
                i++;
            }
            else {
                String buyerEmail = columns.get(0);
                UserAccount buyer = userAccountService.getUserAccountByEmail(buyerEmail);
                String sellerEmail = columns.get(1);
                UserAccount seller = userAccountService.getUserAccountByEmail(sellerEmail);
                String title = columns.get(2);
                Long price = Long.valueOf(columns.get(3));
                String nftLink = columns.get(4);
                Long discounted = Long.valueOf(columns.get(5));

                Transaction transaction =  new Transaction();
                transaction.setBuyer(buyer);
                transaction.setSeller(seller);
                transaction.setTitle(title);
                transaction.setPrice(price);
                transaction.setNftLink(nftLink);
                transaction.setDiscountedPrice(discounted);
                transactionRepository.save(transaction);
            }
        }
    }
    @When("the user tries to view transaction receipt for nfts he sold")
    public void the_user_tries_to_view_transaction_receipt_for_nfts_he_sold() {
        try {
            toDisplay = transactionService.getTransactionsBySeller(userAccount.getId());
        } catch (IllegalArgumentException e) {
            errorCounter++;
            error += e.getMessage();
        }
    }

    @And("an error message {string} shall be raised for transaction")
    public void anErrorMessageShallBeRaisedForTransaction(String arg0) {
        assertTrue(error.contains(arg0));
    }

    @Then("no new transaction receipt shall be generated")
    public void no_new_transaction_receipt_shall_be_generated() {
        assertEquals(numberOfTransactions, transactionService.getTransactions().size());
    }

}
