package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestListingPersistence {

    @Autowired
    private ListingRepository listingRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;


    @AfterEach
    private void clearDatabase(){
        listingRepository.deleteAll();
        userAccountRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadListingByListingID(){

        // Creating a listing
        String title = "Manky";
        Long price = 123L;
        String nftLink = "www.mankey.com/123";

        String firstName = "Joe";
        String lastName = "Doe";
        String userEmail = "joe.doe@mcgill.ca";
        String username = "user123";
        String password = "password123";
        boolean isLoggedIn = false;
        int loginAttempts = 0;
        LocalDateTime lastAttempt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        UserAccount.UserRole userRole = UserAccount.UserRole.Customer;

        UserAccount userAccount = new UserAccount(firstName, lastName, userEmail, username, password, isLoggedIn, loginAttempts, lastAttempt, userRole);
        userAccountRepository.save(userAccount);

        Listing listing = new Listing(title, price, nftLink, userAccount);
//        listing.setListingID(listingID);
//        listing.setTitle(title);
//        listing.setPrice(price);
//        listing.setNftLink(nftLink);

        // Saving listing to repo
        listingRepository.save(listing);
        Long listingID = listing.getListingID();

        // Finding the listing through the ID
        listing = null;
        listing = listingRepository.findListingByListingID(listingID);

        // Assertions
        assertNotNull(listing);
        assertEquals(listingID, listing.getListingID());
        assertEquals(title, listing.getTitle());
        assertEquals(price, listing.getPrice());
        assertEquals(nftLink, listing.getNftLink());
        assertEquals(userAccount.getId(), listing.getOwner().getId());
    }


}
