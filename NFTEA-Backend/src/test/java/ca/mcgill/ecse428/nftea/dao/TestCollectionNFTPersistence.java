package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.CollectionNFT;
import ca.mcgill.ecse428.nftea.model.Listing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCollectionNFTPersistence {

    @Autowired
    private CollectionNFTRepository collectionNFTRepository;
    
    @Autowired
    private ListingRepository listingRepository;

    @AfterEach
    public void clearDatabase() {
        //Clear database with dependencies first
        collectionNFTRepository.deleteAll();
        listingRepository.deleteAll();
    }

    @Test
    public void testPersistAndCollectionNFTByCollectionID() {
        String title = "My first collection";

        //Creating a CollectionNFT with 1 listing
        CollectionNFT collectionNFT = new CollectionNFT();
        Listing listing = new Listing();
        collectionNFT.setTitle(title);
        collectionNFT.addListing(listing);

        //Saving the CollectionNFT to database
        collectionNFTRepository.save(collectionNFT);
        Long collectionID = collectionNFT.getCollectionID();
        

        collectionNFT = null;

        //Get the CollectionNFT from the database
        collectionNFT = collectionNFTRepository.findByCollectionID(collectionID);

        //Assertions
        assertNotNull(collectionNFT);
        assertEquals(collectionID, collectionNFT.getCollectionID());
        assertEquals(title, collectionNFT.getTitle());
        assertNotNull(collectionNFT.getListings());
        assertEquals(collectionNFT.getListings().size(), 1);
    }

}
