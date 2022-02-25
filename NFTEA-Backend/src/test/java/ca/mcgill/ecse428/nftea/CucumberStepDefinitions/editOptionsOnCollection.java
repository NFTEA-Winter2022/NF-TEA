package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.dao.CollectionNFTRepository;
import ca.mcgill.ecse428.nftea.dao.ListingRepository;
import ca.mcgill.ecse428.nftea.model.CollectionNFT;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.CollectionNFTService;
import ca.mcgill.ecse428.nftea.service.ListingService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
public class editOptionsOnCollection {
    private CollectionNFT collectionNFT = null;
    private Listing listing = null;
    private String error = "";
    private int size = 0;

    @Autowired
    CollectionNFTService collectionNFTService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    ListingService listingService;

    @After
    public void teardown(){
        collectionNFTService.clear();

        userAccountService.clear();
        collectionNFT = null;
        listing = null;
        error = "";
        size = 0;
    }

    @And("a collection of NFTs:")
    public void aCollectionOfNFTs(io.cucumber.datatable.DataTable dataTable) throws Exception{
        List<List<String>> rows = dataTable.asLists();
        int i = 0;
        for (List<String> columns : rows){
            if (i == 0){
                i++;
            }
            else {
                Long numberID = Long.parseLong(columns.get(0));
                String title = columns.get(1);
                String[] temp = columns.get(2).split(",");
                List<Long> listOfNFTs = null;
                for (String word: temp) {
                    listOfNFTs.add(Long.parseLong(word));
                }

                //TODO: Create a collection
            }
        }
    }

    @And("some NFTs:")
    public void someNFTs(io.cucumber.datatable.DataTable dataTable) throws Exception{
        List<List<String>> rows = dataTable.asLists();
        int i = 0;
        for (List<String> columns : rows){
            if (i == 0){
                i++;
            }
            else {
                Long listingID = Long.parseLong(columns.get(0));
                String title = columns.get(1);
                Long price = Long.parseLong(columns.get(2));
                String nftLink = columns.get(3);
                String ownerEmail = columns.get(4);

                UserAccount userAccount = userAccountService.getUserAccountByEmail(ownerEmail);
                Listing listing = listingService.createListing(title, price, nftLink, userAccount);
                listing.setListingID(listingID);
                listingService.saveListing(listing);
                
            }
        }
    }

    @Given("there is an online user:")
    public void thereIsAnOnlineUser(io.cucumber.datatable.DataTable dataTable) throws Exception{
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

                UserAccount userAccount = userAccountService.createUser(firstName, lastName, username, email, password);
                userAccountService.saveAccount(userAccount);

            }
        }
    }

    @When("a user wants to add a NFT {string} to the collection {string}")
    public void aUserWantsToAddANFTtoTheCollection(String listingID, String collectionID){
        long longListingID = Long.parseLong(listingID);
        long longCollectionID = Long.parseLong(collectionID);
        try{
            collectionNFT = collectionNFTService.addListing(longCollectionID, longListingID);
            size = collectionNFTService.getCollectionNFTByCollectionID(longCollectionID).numberOfListings();
        }
        catch (Exception e){
            error = e.getMessage();
        }
    }

    @Then("the NFT {string} shall be added to the collection {string}")
    public void theNFTShallBeAddedToTheCollection(String listingID, String collectionID){
        CollectionNFT collectionNFT = collectionNFTService.getCollectionNFTByCollectionID(
                Long.parseLong(collectionID));
        assertEquals(size, collectionNFT.numberOfListings());

    }

    @When("a user wants to remove a NFT {string} to the collection {string}")
    public void aUserWantsToRemoveANFTToTheCollection(String listingID, String collectionID){
        long longListingID = Long.parseLong(listingID);
        long longCollectionID = Long.parseLong(collectionID);
        try{
            collectionNFT = collectionNFTService.removeListing(longCollectionID, longListingID);
            size = collectionNFTService.getCollectionNFTByCollectionID(longCollectionID).numberOfListings();
        }
        catch (Exception e){
            error = e.getMessage();
        }
    }

    @Then("the NFT {string} will not exist in the collection {string}")
    public void theNFTWillNotExistInTheCollection(String listingID, String collectionID){
        CollectionNFT collectionNFT = collectionNFTService.getCollectionNFTByCollectionID(
                Long.parseLong(collectionID));
        assertEquals(size, collectionNFT.numberOfListings());
    }

    @When("a user changes the name {string} of the collection {string}")
    public void aUserChangesTheNameOfTheCollection(String newTitle, String collectionID){
        try{
            collectionNFT = collectionNFTService.updateCollectionTitle(Long.parseLong(collectionID),newTitle);
        }
        catch (Exception e){
            error = e.getMessage();
        }
    }

    @Then("the collection's name {string} will be changed {string}")
    public void theCollectionsNameWillBeChanged(String collectionID, String newTitle){
        CollectionNFT collectionNFT = collectionNFTService.getCollectionNFTByCollectionID(
                Long.parseLong(collectionID));
        assertEquals(newTitle, collectionNFT.getTitle());
    }





}
