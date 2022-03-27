package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ListingRepository extends CrudRepository<Listing, Long> {
    Listing findListingByListingID(Long listingID);

    ArrayList<Listing> findListingBytitle(String title);

    ArrayList<Listing> findListingByprice(Long price);

    ArrayList<Listing> findListingBynftLink(String nftLink);

    Boolean existsBynftLink(String nftLink);

    ArrayList<Listing> findListingByTitleContains(String title);

    ArrayList<Listing> findListingByOwner(UserAccount userAccount);

}
