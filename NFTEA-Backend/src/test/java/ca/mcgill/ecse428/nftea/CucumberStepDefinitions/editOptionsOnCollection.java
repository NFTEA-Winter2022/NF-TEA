package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.dao.CollectionNFTRepository;
import ca.mcgill.ecse428.nftea.dao.ListingRepository;
import ca.mcgill.ecse428.nftea.model.CollectionNFT;
import ca.mcgill.ecse428.nftea.model.Listing;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertTrue;


@SpringBootTest
public class editOptionsOnCollection {
    private CollectionNFT collectionNFT = null;
    private Listing listing = null;

    @Autowired
    CollectionNFTRepository collectionNFTRepository;

    @Autowired
    ListingRepository listingRepository;

    @After
    public void teardown(){
        collectionNFTRepository.deleteAll();
        listingRepository.deleteAll();
        collectionNFT = null;
        listing = null;
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

    @Given("some NFTs:")
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

                //TODO: Create and save Listing
            }
        }
    }

    @When("a user wants to add a NFT {string} to the collection {string}")
    public void aUserWantsToAddANFTtoTheCollection(String NFT, String collectionID){
        // try catch with the add method
    }

    @Then("the NFT {string} shall be added to the collection {string}")
    public void theNFTShallBeAddedToTheCollection(String NFT, String collectionID){
        Long NFTID = Long.parseLong(NFT);
        Long collectionNumberID = Long.parseLong(collectionID);
        //assertTrue();

    }



}
