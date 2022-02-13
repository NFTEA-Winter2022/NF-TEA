package ca.mcgill.ecse428.nftea.service;

import ca.mcgill.ecse428.nftea.dao.UserAccountRepository;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class LogOutService {
    @Autowired
    private UserAccountRepository userAccountRepository;


    @Transactional
    public boolean LogOutAccount(Long NumberID){
        if (NumberID <= 0 ){
            throw new IllegalArgumentException("Please enter a valid ID");
        }

        UserAccount userAccount = userAccountRepository.findUserAccountByNumberID(NumberID);
        if(userAccount == null || !userAccount.getIsLoggedIn()) throw new NullPointerException("Cannot find User wit this ID");
        if (userAccount.setIsLoggedIn(false)){
            userAccountRepository.save(userAccount);
        }
        return false;
    }




}
