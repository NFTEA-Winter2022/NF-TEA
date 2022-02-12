package ca.mcgill.ecse428.nftea.service;

import ca.mcgill.ecse428.nftea.dao.UserAccountRepository;
import ca.mcgill.ecse428.nftea.exception.WrongInputException;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
                throw new WrongInputException(HttpStatus.BAD_REQUEST, "User not found");
            }
            else if(userAccount.getLoginAttempts() >= MAX_ATTEMPTS &&
                    LocalDateTime.now().isBefore(userAccount.getLastAttempt().plusMinutes(ATTEMPTS_COOLDOWN_IN_MINUTES))) {
                throw new WrongInputException(HttpStatus.BAD_REQUEST, "Too many failed attempts, try again later");
            }
            else if (!userAccount.getPassword().equals(password)){
                updateAttempts(email);
                if(userAccount.getLoginAttempts() == MAX_ATTEMPTS) {
                    throw new WrongInputException(HttpStatus.BAD_REQUEST, "Wrong Password, account is locked out");
                }
                throw new WrongInputException(HttpStatus.BAD_REQUEST, "Wrong Password");
            }
            else {
                resetAttempts(email);
                userAccount.setIsLoggedIn(true);
                return userAccount;
            }
        }
    }

    /**
     * Method to update the number of failed login attempts and the last attempt timestamp.
     * @param email The email associated with an account.
     * @return True if update was successful, false if account not found.
     */
    @Transactional
    private boolean updateAttempts(String email) {
        if(userAccountRepository.findUserAccountByUserEmail(email) != null) {
            UserAccount userAccount = userAccountRepository.findUserAccountByUserEmail(email);
            userAccount.setLoginAttempts(userAccount.getLoginAttempts() + 1);
            userAccount.setLastAttempt(LocalDateTime.now());

            userAccountRepository.save(userAccount);
        } else {
            return false;
        }
        return true;
    }

    /**
     * Method to reset the number of failed attempts.
     * @param email The email associated with an account.
     * @return True if reset was successful, false if account not found.
     */
    @Transactional
    private boolean resetAttempts(String email) {
        if(userAccountRepository.findUserAccountByUserEmail(email) != null) {
            UserAccount userAccount = userAccountRepository.findUserAccountByUserEmail(email);
            userAccount.setLoginAttempts(0);

            userAccountRepository.save(userAccount);
        } else {
            return false;
        }
        return true;
    }

}
