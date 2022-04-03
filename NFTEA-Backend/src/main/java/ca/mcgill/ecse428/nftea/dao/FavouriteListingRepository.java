package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.FavouriteListing;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface FavouriteListingRepository extends CrudRepository<FavouriteListing,Long> {
    FavouriteListing findFavouriteListingById(long id);
    ArrayList<FavouriteListing> findFavouriteListingByUser(UserAccount user);
    boolean existsByUserAndListing(UserAccount user, Listing listing);
    FavouriteListing findFavouriteListingByUserAndListing(UserAccount user, Listing listing);
    void deleteByUserAndListing(UserAccount user, Listing listing);
}
