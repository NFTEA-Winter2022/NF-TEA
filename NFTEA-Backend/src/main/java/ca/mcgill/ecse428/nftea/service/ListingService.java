package ca.mcgill.ecse428.nftea.service;

import ca.mcgill.ecse428.nftea.dao.CollectionNFTRepository;
import ca.mcgill.ecse428.nftea.dao.ListingRepository;
import ca.mcgill.ecse428.nftea.dao.UserAccountRepository;
import ca.mcgill.ecse428.nftea.model.CollectionNFT;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private CollectionNFTRepository collectionNFTRepository;

    @Transactional
    public List<Listing> getUnclassifiedListingsFromCollection(long collectionID) throws IllegalArgumentException{
        if (collectionNFTRepository.findByCollectionID(collectionID) == null) {
            throw new IllegalArgumentException("Collection not found");
        }
        CollectionNFT collectionNFT = collectionNFTRepository.findByCollectionID(collectionID);
        UserAccount collectionOwner = userAccountRepository.findUserAccountByCollectionNFTsContaining(collectionNFT);

        List<Listing> allListings = listingRepository.findByOwner(collectionOwner);
        for(CollectionNFT c : collectionOwner.getCollectionNFTs()) {
            List<Listing> l = c.getListings();
            allListings.removeAll(l);
        }
        return allListings;
    }
}
