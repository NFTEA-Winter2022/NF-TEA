package ca.mcgill.ecse428.nftea.CucumberStepDefinitions;

import ca.mcgill.ecse428.nftea.dao.CollectionNFTRepository;
import ca.mcgill.ecse428.nftea.model.CollectionNFT;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class deleteCollectionStepDefinitions {

    CollectionNFT collectionNFT = null;

    @Autowired
    CollectionNFTRepository collectionNFTRepository;
    // Make a service class for collection of NFT

    @After
    public void teardown(){
        collectionNFTRepository.deleteAll();
    }

    @Given("a collection of NFT:")
    public void aCollectionOfNFT(io.cucumber.datatable.DataTable dataTable) throws Exception{
        List<List<String>> rows = dataTable.asLists();
        int i = 0;
        for (List<String> columns : rows){
            if (i == 0){
                i++;
            }
            else{
                Long collectionID = Long.parseLong(columns.get(0));
                String title = columns.get(1);
                ArrayList<Long> listOfNFT = new ArrayList<>();
                String[] temp = columns.get(2).split(",");
                for (String w: temp) {
                    listOfNFT.add(Long.parseLong(w));
                }
                // TODO: Create a collec

            }
        }
    }
}
