package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.CollectionNFT;
import org.springframework.data.repository.CrudRepository;

public interface CollectionNFTRepository extends CrudRepository<CollectionNFT,Long> {
    CollectionNFT findByCollectionID(Long collectionID);
}
