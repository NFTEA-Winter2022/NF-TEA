package ca.mcgill.ecse428.nftea.service;

import ca.mcgill.ecse428.nftea.dao.ListingRepository;
import ca.mcgill.ecse428.nftea.dao.UserAccountRepository;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListingService {

    @Autowired
    ListingRepository listingRepository;

    @Transactional
    public Listing createInitialListing(String title, Long price, String nftLink, UserAccount user) throws IllegalArgumentException {
        if (title == null || nftLink == null) {
            throw new IllegalArgumentException("Title and/or NFTLink cannot be empty");
        }
        Listing aListing = new Listing(title, price, nftLink, user);
        listingRepository.save(aListing);
        return aListing;
    }


    @Transactional
    public Listing getListingWithId(Long listingID){
        String error = "";
        if(listingID < 0){
            error += "Listing ID must be positive";
        }
        error = error.trim();
        if (error.length() > 0){
            throw new IllegalArgumentException(error);
        }
        return listingRepository.findListingByListingID(listingID);
    }

    @Transactional
    public List<Listing> getListingsWithTitle(String title){
        String error = "";
        if(title == null){
            error += "Please enter a title";
        }
        error = error.trim();
        if (error.length() > 0){
            throw new IllegalArgumentException(error);
        }
        return listingRepository.findListingByTitle(title);
    }

    @Transactional
    public Listing getListingWithNFTLink (String NFTLink){
        String error = "";
        if(NFTLink == null){
            error += "Please enter a NFT link";
        }
        error = error.trim();
        if (error.length() > 0){
            throw new IllegalArgumentException(error);
        }
        return listingRepository.findListingBynftLink(NFTLink);
    }

    @Transactional
    public List<Listing> getListingsWithOwner (UserAccount userAccount){
        String error = "";
        if(userAccount == null){
            error += "Please enter a valid user account";
        }
        error = error.trim();
        if (error.length() > 0){
            throw new IllegalArgumentException(error);
        }
        return listingRepository.findListingByOwner(userAccount);
    }

    @Transactional
    public List<Listing> getListingsContainingTitle (String title){
        String error = "";
        if(title == null){
            error += "Please enter a title";
        }
        error = error.trim();
        if (error.length() > 0){
            throw new IllegalArgumentException(error);
        }
        return listingRepository.findListingByTitleContains(title);
    }



}
