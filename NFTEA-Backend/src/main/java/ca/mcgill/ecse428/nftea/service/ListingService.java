package ca.mcgill.ecse428.nftea.service;

import ca.mcgill.ecse428.nftea.dao.ListingRepository;
import ca.mcgill.ecse428.nftea.dao.UserAccountRepository;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ListingService {
    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Transactional
    public Listing createListing(String aTitle, Long aPrice, String aNftLink, UserAccount userAccount){
        Listing listing = new Listing(aTitle, aPrice, aNftLink, userAccount);
        listingRepository.save(listing);
        return listing;
    }

    @Transactional
    public void saveListing(Listing listing) {listingRepository.save(listing);}

    @Transactional
    public void clear() {listingRepository.deleteAll();}
}
