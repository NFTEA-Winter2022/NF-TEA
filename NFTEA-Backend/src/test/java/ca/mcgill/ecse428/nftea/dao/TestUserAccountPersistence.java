package ca.mcgill.ecse428.nftea.dao;

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
public class TestUserAccountPersistence {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @AfterEach
    public void clearDatabase() {
        userAccountRepository.deleteAll();;
    }

    @Test
    public void testPersistAndLoadUserAccountByNumberID() {
        Long numberID = 12345000000L;
        String firstName = "Joe";
        String lastName = "Doe";
        String userEmail = "joe.doe@mcgill.ca";
        String username = "user123";
        String password = "password123";
        boolean isLoggedIn = false;
        int loginAttempts = 0;
        UserAccount.UserRole userRole = UserAccount.UserRole.Customer;

        //Creating an UserAccount
        UserAccount userAccount = new UserAccount(numberID, firstName, lastName, userEmail, username, password, isLoggedIn, loginAttempts, userRole);

        //Save UserAccount to database
        userAccountRepository.save(userAccount);

        userAccount = null;

        //Get UserAccount from the database
        userAccount = userAccountRepository.findUserAccountByNumberID(numberID);

        //Assertions
        assertNotNull(userAccount);
        assertEquals(numberID, userAccount.getNumberID());
        assertEquals(firstName, userAccount.getFirstName());
        assertEquals(lastName, userAccount.getLastName());
        assertEquals(userEmail, userAccount.getUserEmail());
        assertEquals(username, userAccount.getUsername());
        assertEquals(password, userAccount.getPassword());
        assertEquals(isLoggedIn, userAccount.isIsLoggedIn());
        assertEquals(userRole, userAccount.getUserRole());
    }

}
