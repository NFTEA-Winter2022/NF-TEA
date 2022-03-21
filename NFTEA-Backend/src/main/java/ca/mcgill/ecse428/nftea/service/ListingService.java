package ca.mcgill.ecse428.nftea.service;

import ca.mcgill.ecse428.nftea.dao.ListingRepository;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Listing editTitle(Long ID, String title) throws IllegalArgumentException {
        Listing listing = listingRepository.findListingByListingID(ID);
        if(listing == null) {
            throw new IllegalArgumentException("listing not found");
        }
        if(title == null || title == "") {
            throw new IllegalArgumentException("title cannot be empty");
        }

        listing.setTitle(title);
        return listingRepository.save(listing);
    }

    @Transactional
    public Listing editPrice(Long ID, Long price) throws IllegalArgumentException {
        Listing listing = listingRepository.findListingByListingID(ID);
        if(listing == null) {
            throw new IllegalArgumentException("listing not found");
        }
        if(price < 0) {
            throw new IllegalArgumentException("price cannot be negative");
        }

        listing.setPrice(price);
        return listingRepository.save(listing);
    }

    @Transactional
    public Listing editNftLink(Long ID, String nftLink) throws IllegalArgumentException {
        Listing listing = listingRepository.findListingByListingID(ID);
        if(listing == null) {
            throw new IllegalArgumentException("listing not found");
        }
        if(nftLink == null || nftLink == "") {
            throw new IllegalArgumentException("NFT link cannot be empty");
        }

        listing.setNftLink(nftLink);
        return listingRepository.save(listing);
    }

    @Transactional
    public ArrayList<Listing> getListings() {
        ArrayList<Listing> listings = new ArrayList<>();
        for (Listing mylisting : listingRepository.findAll()) {
            listings.add(mylisting);
        }
        return listings;
    }

    public ArrayList<Listing> getListingsByTitle(String title) {
        ArrayList<Listing> listings = new ArrayList<>();
        for (Listing mylisting : listingRepository.findListingBytitle(title)) {
            listings.add(mylisting);
        }
        return listings;
    }

    public ArrayList<Listing> getListingsByPrice(long price) {
        ArrayList<Listing> listings = new ArrayList<>();
        for (Listing mylisting : listingRepository.findListingByprice(price)) {
            listings.add(mylisting);
        }
        return listings;
    }

    public ArrayList<Listing> getListingsByNFTLink(String nftLink) {
        ArrayList<Listing> listings = new ArrayList<>();
        for (Listing mylisting : listingRepository.findListingBynftLink(nftLink)) {
            listings.add(mylisting);
        }
        return listings;
    }

    public Listing getListingByID(long id) {
        return listingRepository.findListingByListingID(id);
    }


    @Transactional
    public void clear() {
        listingRepository.deleteAll();
    }


    public ArrayList<Listing> getListingsContainingTitle(String title) {
        ArrayList<Listing> listings = new ArrayList<>();
        for (Listing mylisting : listingRepository.findListingByTitleContains(title)) {
            listings.add(mylisting);
        }
        return listings;
    }

    public ArrayList<Listing> getListingsWithOwner(UserAccount user) {
        ArrayList<Listing> listings = new ArrayList<>();
        for (Listing mylisting : listingRepository.findListingByOwner(user)) {
            listings.add(mylisting);
        }
        return listings;
    }
}
