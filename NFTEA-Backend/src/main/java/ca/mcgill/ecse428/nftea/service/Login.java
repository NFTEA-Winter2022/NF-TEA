package ca.mcgill.ecse428.nftea.service;

import ca.mcgill.ecse428.nftea.dao.UserAccountRepository;
import ca.mcgill.ecse428.nftea.exception.WrongInputException;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Login {

    @Autowired
    private UserAccountRepository userAccountRepository;

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
            else if (!userAccount.getPassword().equals(password)){
                throw new WrongInputException(HttpStatus.BAD_REQUEST, "Wrong Password");
            }
            else {
                return userAccount;
            }
        }
    }
}
