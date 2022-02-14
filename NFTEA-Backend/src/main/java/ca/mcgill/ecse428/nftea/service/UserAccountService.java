package ca.mcgill.ecse428.nftea.service;

import ca.mcgill.ecse428.nftea.dao.UserAccountRepository;
import ca.mcgill.ecse428.nftea.exception.WrongInputException;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Transactional
    public UserAccount createUser(String firstName,String lastName,String userName,String userEmail,String password) throws IllegalArgumentException {
        if (isValidEmailAddress(userEmail)==false){
            throw new IllegalArgumentException("Wrong Email");
        }
        if (password.length()<8){
            throw new IllegalArgumentException("More then 8 chars are required");
        }
        if (firstName.isEmpty()||lastName.isEmpty()||userEmail.isEmpty()||password.isEmpty()||userName.isEmpty()){
            throw new IllegalArgumentException("All textboxes need to be completed");
        }
        UserAccount myUser= new UserAccount(firstName,lastName,userEmail,userName,password,false, 0, null, UserAccount.UserRole.Customer);
        userAccountRepository.save(myUser);
        return myUser;
    }

    @Transactional
    public UserAccount getAccount(Long userid) {
        return userAccountRepository.findUserAccountById(userid);
    }

    @Transactional
    public UserAccount setNewPassword(Long userid,  String confirmPassword, String newPassword) throws Exception {
        UserAccount user = getAccount(userid);
        if (!user.getPassword().equals(confirmPassword)) throw new Exception("Passwords do not match");
        if (newPassword.length()<8)throw new Exception("More then 8 chars are required");
        user.setPassword(newPassword);
        userAccountRepository.save(user);
        return user;
    }

    @Transactional
    public UserAccount setNewUsername(Long userid, String newUsername) throws Exception {
        UserAccount user = getAccount(userid);
        if (newUsername.length() == 0) throw new Exception("Username Invalid");
        user.setUsername(newUsername);
        userAccountRepository.save(user);
        return user;
    }

    @Transactional
    public UserAccount setUserOnline(String username) throws Exception {
        UserAccount user = userAccountRepository.findUserAccountByUsername(username);
        if (user == null) throw new Exception("Invalid username");
        user.setIsLoggedIn(true);
        userAccountRepository.save(user);
        return user;
    }

//    @Transactional
//    public UserAccount changeUserInfo(String username, String newPassWord, String confirmPassword, String userEmail) throws Exception {
//        if (username.length() == 0) throw new Exception("Invalid username");
//        if (!isValidEmailAddress(userEmail)) throw new Exception("Invalid Email");
//        if (newPassWord.length()<8 || confirmPassword.length() < 8) throw new Exception("Passwords cannot be empty");
//        if (!newPassWord.equals(confirmPassword)) throw new Exception("Passwords do not match");
//        UserAccount user = userAccountRepository.findUserAccountByUsername(username);
//        user.setUsername(username);
//        user.setPassword(newPassWord);
//        user.setUserEmail(userEmail);
//        userAccountRepository.save(user);
//        return user;
//    }

    @Transactional
    public void clear() {
        userAccountRepository.deleteAll();
	}

	@Transactional
    public UserAccount getUserAccountByEmail(String email) {
        String error = "";
        if(email == null || email.trim() == "") {
            error += ("Invalid email");
        }
        error.trim();
        if(error.length() > 0) {
            throw new WrongInputException(HttpStatus.BAD_REQUEST, error);
        }
        return userAccountRepository.findUserAccountByUserEmail(email);
    }
    
    @Transactional
    public void deleteUser(Long id, String password) throws Exception {
    	if(id == null) {
    		throw new Exception("User Id required to delete user.");
    	}
    	if(password == null || password.length() < 8) {
    		throw new Exception("Valid password required to delete user.");
    	}
    	
    	UserAccount user = userAccountRepository.findUserAccountById(id);
    	
    	// User is not in the Database, as is requested by the callee
    	if(user == null) {
    		return;
    	} else if (!user.getPassword().equals(password)) {
    		throw new Exception("Invalid password for user " + id + ".");
    	} else {
    		userAccountRepository.delete(user);
    	}
    }
    

    public boolean isValidEmailAddress(String email) {
//        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
//        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
//        java.util.regex.Matcher m = p.matcher(email);
//        return m.matches();
        return true;
    }

    @Transactional
    public void saveAccount(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }
}
