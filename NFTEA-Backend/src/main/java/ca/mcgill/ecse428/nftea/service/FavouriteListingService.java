package ca.mcgill.ecse428.nftea.service;
import ca.mcgill.ecse428.nftea.dao.FavouriteListingRepository;
import ca.mcgill.ecse428.nftea.dao.ListingRepository;
import ca.mcgill.ecse428.nftea.dao.UserAccountRepository;
import ca.mcgill.ecse428.nftea.model.FavouriteListing;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class FavouriteListingService {

    @Autowired
    FavouriteListingRepository favouriteListing;
    @Autowired
    UserAccountRepository user;
    @Autowired
    ListingRepository listing;

    @Transactional
    public FavouriteListing createFavourite(UserAccount user, Listing listing)throws IllegalArgumentException {
        if (favouriteListing.existsByUserAndListing(user,listing)){
            throw new IllegalArgumentException("Already exists in your favourites");
        }
        FavouriteListing favourite = new FavouriteListing(user,listing);
        favouriteListing.save(favourite);
        return favourite;
    }
    @Transactional
    public FavouriteListing deleteFavourite(Long Id){
        FavouriteListing favourite = favouriteListing.findFavouriteListingById(Id);
        favouriteListing.delete(favourite);
        return favourite;
    }
    @Transactional
    public FavouriteListing deleteByUserAndListing(long user, long listing){
        FavouriteListing favourite = favouriteListing.findFavouriteListingByUserAndListing(this.user.findUserAccountById(user),this.listing.findListingByListingID(listing));
        favouriteListing.deleteByUserAndListing(this.user.findUserAccountById(user),this.listing.findListingByListingID(listing));
        return favourite;
    }
    @Transactional
    public ArrayList<FavouriteListing> getALLFavouriteListings(long user){
        return favouriteListing.findFavouriteListingByUser(this.user.findUserAccountById(user));
    }
    @Transactional
    public FavouriteListing getFavouriteByUserAndListing(long user, long listing){
        return favouriteListing.findFavouriteListingByUserAndListing(this.user.findUserAccountById(user),this.listing.findListingByListingID(listing));
    }



}
