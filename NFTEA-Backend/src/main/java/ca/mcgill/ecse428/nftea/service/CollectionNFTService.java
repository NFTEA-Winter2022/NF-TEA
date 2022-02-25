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
public class CollectionNFTService {

    @Autowired
    private CollectionNFTRepository collectionNFTRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private ListingRepository listingRepository;

    @Transactional
    public List<CollectionNFT> getCollectionNFTByUser(long userID) throws IllegalArgumentException{
        if(userAccountRepository.findUserAccountById(userID) == null) {
            throw new IllegalArgumentException("User not found");
        }
        UserAccount user = userAccountRepository.findUserAccountById(userID);
        return user.getCollectionNFTs();
    }

    @Transactional
    public CollectionNFT getCollectionNFTByCollectionID(long collectionID) throws IllegalArgumentException{
        CollectionNFT collectionNFT = collectionNFTRepository.findByCollectionID(collectionID);
        if(collectionNFT == null) {
            throw new IllegalArgumentException("Collection not found");
        }
        return collectionNFT;
    }

    @Transactional
    public CollectionNFT updateCollectionTitle(long collectionID, String title) {
        if(collectionNFTRepository.findByCollectionID(collectionID) == null) {
            throw new IllegalArgumentException("Collection not found");
        }
        CollectionNFT collectionNFT = collectionNFTRepository.findByCollectionID(collectionID);
        collectionNFT.setTitle(title);
        collectionNFTRepository.save(collectionNFT);
        return collectionNFT;
    }

    @Transactional
    public CollectionNFT addListing(long collectionID, long listingID) throws IllegalArgumentException{
        if(collectionNFTRepository.findByCollectionID(collectionID) == null) {
            throw new IllegalArgumentException("Collection not found");
        }
        if(listingRepository.findListingByListingID(listingID) == null) {
            throw new IllegalArgumentException("Listing not found");
        }
        CollectionNFT collectionNFT = collectionNFTRepository.findByCollectionID(collectionID);
        for(Listing l : collectionNFT.getListings()) {
            if(listingID == l.getListingID()) {
                throw new IllegalArgumentException("Listing is already in the collection");
            }
        }
        Listing listing = listingRepository.findListingByListingID(listingID);
        UserAccount collectionOwner = userAccountRepository.findUserAccountByCollectionNFTsContaining(collectionNFT);
        if(collectionOwner.getId() != listing.getOwner().getId()) {
            throw new IllegalArgumentException("This listing does not belong to you");
        }
        collectionNFT.addListing(listing);
        collectionNFTRepository.save(collectionNFT);
        return collectionNFT;
    }

    @Transactional
    public CollectionNFT addListings(long collectionID, List<Long> listingIDs) throws IllegalArgumentException{
        if(collectionNFTRepository.findByCollectionID(collectionID) == null) {
            throw new IllegalArgumentException("Collection not found");
        }
        CollectionNFT collectionNFT = collectionNFTRepository.findByCollectionID(collectionID);
        UserAccount collectionOwner = userAccountRepository.findUserAccountByCollectionNFTsContaining(collectionNFT);
        for(long l : listingIDs) {
            Listing listingToAdd = listingRepository.findListingByListingID(l);
            if(listingToAdd == null) {
                throw new IllegalArgumentException("Some listings not found");
            }
            if(listingToAdd.getOwner().getId() != collectionOwner.getId()) {
                throw new IllegalArgumentException("Some listings do not belong to you");
            }
            for(Listing listing : collectionNFT.getListings()) {
                if(listing.getListingID() == l) {
                    throw new IllegalArgumentException("Some listings were not found in collection");
                }
            }
        }
        for(long l : listingIDs) {
            try {
                addListing(collectionID, l);
            } catch (IllegalArgumentException e) {
                System.err.println("Tried adding some listing twice, but I'll let it slide");
            }
        }
        return collectionNFTRepository.findByCollectionID(collectionID);
    }

    @Transactional
    public CollectionNFT removeListing(long collectionID, long listingID) throws IllegalArgumentException{
        if(collectionNFTRepository.findByCollectionID(collectionID) == null) {
            throw new IllegalArgumentException("Collection not found");
        }
        if(listingRepository.findListingByListingID(listingID) == null) {
            throw new IllegalArgumentException("Listing not found");
        }
        CollectionNFT collectionNFT = collectionNFTRepository.findByCollectionID(collectionID);
        List<Listing> listings = collectionNFT.getListings();
        for(int i = 0; i < collectionNFT.getListings().size(); i++) {
            if(collectionNFT.getListings().get(i).getListingID() == listingID){
                collectionNFT.getListings().remove(i);
                collectionNFTRepository.save(collectionNFT);
                return collectionNFT;
            }
        }
        throw new IllegalArgumentException("Listing not found in collection");
    }

    @Transactional
    public CollectionNFT removeListings(long collectionID, List<Long> listingIDs) throws IllegalArgumentException{
        if(collectionNFTRepository.findByCollectionID(collectionID) == null) {
            throw new IllegalArgumentException("Collection not found");
        }
        CollectionNFT collectionNFT = collectionNFTRepository.findByCollectionID(collectionID);
        for(long l : listingIDs) {
            boolean found = false;
            for(Listing listing : collectionNFT.getListings()) {
                if(listing.getListingID() == l) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                throw new IllegalArgumentException("Some listings were not found in collection");
            }
        }
        for(long l : listingIDs) {
            try {
                removeListing(collectionID, l);
            } catch (IllegalArgumentException e) {
                System.err.println("Tried to remove the same listing twice, but I'll let it slide");
            }
        }
        return collectionNFTRepository.findByCollectionID(collectionID);
    }

    @Transactional
    public void clear() {
        collectionNFTRepository.deleteAll();
    }

}
