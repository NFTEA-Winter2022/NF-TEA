package ca.mcgill.ecse428.nftea.service;

import ca.mcgill.ecse428.nftea.dao.UserAccountRepository;
import ca.mcgill.ecse428.nftea.exception.WrongInputException;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class LoginService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    private static final int MAX_ATTEMPTS = 3;
    private static final int ATTEMPTS_COOLDOWN_IN_MINUTES = 15;

    @Transactional
    public UserAccount loginUserAccount(String email, String password){
        if (email == null || password == null){
            throw new WrongInputException(HttpStatus.BAD_REQUEST,"Please enter your email or password");
        }
        else {
            UserAccount userAccount = userAccountRepository.findUserAccountByUserEmail(email);
            if (userAccount == null){
                throw new WrongInputException(HttpStatus.BAD_REQUEST, "Incorrect email/password");
            }
            else if(userAccount.getLoginAttempts() >= MAX_ATTEMPTS &&
                    LocalDateTime.now().isBefore(userAccount.getLastAttempt().plusMinutes(ATTEMPTS_COOLDOWN_IN_MINUTES))) {
                userAccount.setIsLoggedIn(false);
                userAccountRepository.save(userAccount);
                throw new WrongInputException(HttpStatus.BAD_REQUEST, "Too many attempts, please try again later");
            }
            else if (!userAccount.getPassword().equals(password)){
                updateAttempts(userAccount.getId());
                userAccount.setIsLoggedIn(false);
                userAccountRepository.save(userAccount);
                if(userAccount.getLoginAttempts() >= MAX_ATTEMPTS) {
                    throw new WrongInputException(HttpStatus.BAD_REQUEST, "Wrong Password, account is locked out");
                }
                throw new WrongInputException(HttpStatus.BAD_REQUEST, "Incorrect email/password");
            }
            else {
                resetAttempts(userAccount.getId());
                userAccount.setIsLoggedIn(true);
                userAccountRepository.save(userAccount);
                return userAccount;
            }
        }
    }

    /**
     * Method to update the number of failed login attempts and the last attempt timestamp.
     * @param userID The ID associated with an account.
     * @return True if update was successful, false if account not found.
     */
    @Transactional
    private boolean updateAttempts(long userID) {
        if(userAccountRepository.findUserAccountById(userID) != null) {
            UserAccount userAccount = userAccountRepository.findUserAccountById(userID);
            userAccount.setLoginAttempts(userAccount.getLoginAttempts() + 1);
            userAccount.setLastAttempt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            userAccountRepository.save(userAccount);
        } else {
            return false;
        }
        return true;
    }

    /**
     * Method to reset the number of failed attempts.
     * @param userID The email associated with an account.
     * @return True if reset was successful, false if account not found.
     */
    @Transactional
    private boolean resetAttempts(long userID) {
        if(userAccountRepository.findUserAccountById(userID) != null) {
            UserAccount userAccount = userAccountRepository.findUserAccountById(userID);
            userAccount.setLoginAttempts(0);

            userAccountRepository.save(userAccount);
        } else {
            return false;
        }
        return true;
    }

}
