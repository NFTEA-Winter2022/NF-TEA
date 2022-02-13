package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.Listing;
import org.springframework.data.repository.CrudRepository;

public interface ListingRepository extends CrudRepository<Listing,Long> {
    Listing findListingByListingID(Long listingID);
}
