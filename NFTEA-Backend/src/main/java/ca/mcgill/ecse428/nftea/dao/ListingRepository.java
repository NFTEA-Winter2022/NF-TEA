package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListingRepository extends CrudRepository<Listing,Long> {
    Listing findListingByListingID(Long listingID);
    Listing findListingBynftLink(String nftLink);
    Boolean existsBynftLink(String nftLink);
    List<Listing> findListingByTitle(String title);
    List<Listing> findListingByTitleContains(String title);
    List<Listing> findListingByOwner(UserAccount userAccount);
}
