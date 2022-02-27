package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.CollectionNFT;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount,Long> {
    UserAccount findUserAccountById(long id);
    UserAccount findUserAccountByUsername(String username);
    UserAccount findUserAccountByUserEmail(String email);
    UserAccount findUserAccountByCollectionNFTsContaining(CollectionNFT collectionNFT);
}
