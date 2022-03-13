package ca.mcgill.ecse428.nftea.service;

import ca.mcgill.ecse428.nftea.dao.ListingRepository;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Listing editTitle(Long ID, String title) throws IllegalArgumentException {
        listingRepository.findListingByListingID(ID).setTitle(title);
        return listingRepository.findListingByListingID(ID);
    }

    @Transactional
    public Listing editPrice(Long ID, Long price) throws IllegalArgumentException {
        listingRepository.findListingByListingID(ID).setPrice(price);
        return listingRepository.findListingByListingID(ID);
    }

    @Transactional
    public Listing editNftLink(Long ID, String nftLink) throws IllegalArgumentException {
        listingRepository.findListingByListingID(ID).setNftLink(nftLink);
        return listingRepository.findListingByListingID(ID);
    }


}
