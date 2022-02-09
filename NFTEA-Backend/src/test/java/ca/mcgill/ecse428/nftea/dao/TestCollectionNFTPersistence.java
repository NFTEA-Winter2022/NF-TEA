package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.CollectionNFT;
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
public class TestCollectionNFTPersistence {

    @Autowired
    private CollectionNFTRepository collectionNFTRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @AfterEach
    public void clearDatabase() {
        //Clear database with dependencies first
        collectionNFTRepository.deleteAll();
        userAccountRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadUserAccountByCollectionID() {
        Long collectionID = 37290000000L;
        String title = "My first collection";

        //Setting up an user account
        Long numberID = 12345000000L;
        String firstName = "Joe";
        String lastName = "Doe";
        String userEmail = "joe.doe@mcgill.ca";
        String username = "user123";
        String password = "password123";
        boolean isLoggedIn = false;
        UserAccount.UserRole userRole = UserAccount.UserRole.Customer;

        UserAccount userAccount = new UserAccount();
        userAccount.setNumberID(numberID);
        userAccount.setFirstName(firstName);
        userAccount.setLastName(lastName);
        userAccount.setUserEmail(userEmail);
        userAccount.setUsername(username);
        userAccount.setPassword(password);
        userAccount.setIsLoggedIn(isLoggedIn);
        userAccount.setUserRole(userRole);

        userAccountRepository.save(userAccount);

        CollectionNFT collectionNFT = new CollectionNFT();
        collectionNFT.setCollectionID(collectionID);
        collectionNFT.setTitle(title);
        collectionNFT.setUserAccount(userAccount);

        collectionNFTRepository.save(collectionNFT);

        collectionNFT = null;

        collectionNFT = collectionNFTRepository.findByCollectionID(collectionID);

        assertNotNull(collectionNFT);
        assertEquals(collectionID, collectionNFT.getCollectionID());
        assertEquals(title, collectionNFT.getTitle());
        assertNotNull(collectionNFT.getUserAccount());
        assertEquals(numberID, collectionNFT.getUserAccount().getNumberID());
        assertEquals(firstName, collectionNFT.getUserAccount().getFirstName());
        assertEquals(lastName, collectionNFT.getUserAccount().getLastName());
        assertEquals(userEmail, collectionNFT.getUserAccount().getUserEmail());
        assertEquals(username, collectionNFT.getUserAccount().getUsername());
        assertEquals(password, collectionNFT.getUserAccount().getPassword());
        assertEquals(isLoggedIn, collectionNFT.getUserAccount().isIsLoggedIn());
        assertEquals(userRole, collectionNFT.getUserAccount().getUserRole());

    }

}
