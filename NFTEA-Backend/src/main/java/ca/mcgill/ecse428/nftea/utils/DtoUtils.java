package ca.mcgill.ecse428.nftea.utils;

import ca.mcgill.ecse428.nftea.dto.UserAccountDto;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;

public class DtoUtils {

    @Autowired
    private static UserAccountService userAccountService;

    public static UserAccountDto convertToDto (UserAccount cu)
    {
        if (cu == null){
            throw new IllegalArgumentException("User does not exist");
        }
        UserAccountDto userAccountDto = new UserAccountDto(cu.getId(), cu.getFirstName(), cu.getLastName(), cu.getUserEmail(),
                cu.getUsername(), cu.getPassword(), cu.getIsLoggedIn(), cu.getLoginAttempts(), cu.getLastAttempt(), cu.getUserRole());
        return userAccountDto;
    }
}
