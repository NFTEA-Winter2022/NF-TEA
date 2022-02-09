package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.CollectionNFT;
import ca.mcgill.ecse428.nftea.model.Listing;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListingRepository extends CrudRepository<Listing,Long> {
    List<Listing> findByCollectionNFTsIn(List<CollectionNFT> collectionNFTS);
}
