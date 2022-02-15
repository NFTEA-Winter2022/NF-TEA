package ca.mcgill.ecse428.nftea.service;

import ca.mcgill.ecse428.nftea.dao.UserAccountRepository;
import ca.mcgill.ecse428.nftea.exception.WrongInputException;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    private static final int MAX_ATTEMPTS = 3;
    private static final int ATTEMPTS_COOLDOWN_IN_MINUTES = 15;

    private Clock clock = Clock.systemDefaultZone();

    @Transactional(noRollbackFor = WrongInputException.class)
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
                    LocalDateTime.now(clock).isBefore(userAccount.getLastAttempt().plusMinutes(ATTEMPTS_COOLDOWN_IN_MINUTES))) {
                userAccount.setIsLoggedIn(false);
                userAccountRepository.save(userAccount);
                throw new WrongInputException(HttpStatus.BAD_REQUEST, "Too many attempts, please try again later");
            }
            else if (!userAccount.getPassword().equals(password)){
                // update attempts
                userAccount.setLoginAttempts(userAccount.getLoginAttempts() + 1);
                userAccount.setLastAttempt(LocalDateTime.now(clock).truncatedTo(ChronoUnit.SECONDS));

                userAccount.setIsLoggedIn(false);
                userAccountRepository.save(userAccount);
                if(userAccount.getLoginAttempts() >= MAX_ATTEMPTS) {
                    throw new WrongInputException(HttpStatus.BAD_REQUEST, "Wrong Password, account is locked out");
                }
                throw new WrongInputException(HttpStatus.BAD_REQUEST, "Incorrect email/password");
            }
            else {
                userAccount.setLoginAttempts(0);
                userAccount.setIsLoggedIn(true);
                userAccountRepository.save(userAccount);
                return userAccount;
            }
        }
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

}
