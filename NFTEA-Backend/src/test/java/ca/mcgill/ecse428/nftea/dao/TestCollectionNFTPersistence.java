package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.CollectionNFT;
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
public class TestCollectionNFTPersistence {

    @Autowired
    private CollectionNFTRepository collectionNFTRepository;
    
    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

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
        //Setting up an user account
        Long numberID = 12345000000L;
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
//        userAccount.setNumberID(numberID);
//        userAccount.setFirstName(firstName);
//        userAccount.setLastName(lastName);
//        userAccount.setUserEmail(userEmail);
//        userAccount.setUsername(username);
//        userAccount.setPassword(password);
//        userAccount.setIsLoggedIn(isLoggedIn);
//        userAccount.setUserRole(userRole);

        //Saving UserAccount to database
        userAccountRepository.save(userAccount);

        //Creating a CollectionNFT
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
