package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.CollectionNFT;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface CollectionNFTRepository extends CrudRepository<CollectionNFT,Long> {
    List<CollectionNFT> findByUserAccount(UserAccount userAccount);
    List<CollectionNFT> findByCollectionID(Long collectionID);
}
