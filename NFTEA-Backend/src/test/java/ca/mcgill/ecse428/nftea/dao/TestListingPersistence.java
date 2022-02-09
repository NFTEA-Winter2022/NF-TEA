package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
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
public class TestListingPersistence {

    @Autowired
    private ListingRepository listingRepository;

    @AfterEach
    private void clearDatabase(){
        listingRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadListingByListingID(){

        // Creating a listing
        Long listingID = 12345L;
        String title = "Manky";
        Long price = 123L;
        String nftLink = "www.mankey.com/123";

        Listing listing = new Listing();
        listing.setListingID(listingID);
        listing.setTitle(title);
        listing.setPrice(price);
        listing.setNftLink(nftLink);

        // Saving listing to repo
        listingRepository.save(listing);

        // Finding the listing through the ID
        listing = null;
        listing = listingRepository.findListingByListingID(listingID);

        // Assertions
        assertNotNull(listing);
        assertEquals(listingID, listing.getListingID());
        assertEquals(title, listing.getTitle());
        assertEquals(price, listing.getPrice());
        assertEquals(nftLink, listing.getNftLink());
    }


}
