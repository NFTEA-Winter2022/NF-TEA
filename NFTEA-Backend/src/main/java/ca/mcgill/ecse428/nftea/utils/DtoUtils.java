package ca.mcgill.ecse428.nftea.utils;

import ca.mcgill.ecse428.nftea.dto.CollectionNFTDto;
import ca.mcgill.ecse428.nftea.dto.ListingDto;
import ca.mcgill.ecse428.nftea.dto.NotificationDto;
import ca.mcgill.ecse428.nftea.dto.UserAccountDto;
import ca.mcgill.ecse428.nftea.model.CollectionNFT;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.Notification;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DtoUtils {

    @Autowired
    private static UserAccountService userAccountService;

    public static UserAccountDto convertToDto (UserAccount cu)
    {
        if (cu == null){
            throw new IllegalArgumentException("User does not exist");
        }
        UserAccountDto userAccountDto = new UserAccountDto(cu.getId(), cu.getFirstName(), cu.getLastName(), cu.getUserEmail(),
                cu.getUsername(), cu.getPassword(), cu.getIsLoggedIn(), cu.getLoginAttempts(), cu.getLastAttempt(), cu.getUserRole());
        return userAccountDto;
    }

    public static ListingDto convertToDto(Listing listing) {
        if(listing == null) {
            throw new IllegalArgumentException("Listing does not exist");
        }
        ListingDto listingDto = new ListingDto(listing.getListingID(), listing.getTitle(), listing.getPrice(), listing.getNftLink());
        listingDto.setOwner(convertToDto(listing.getOwner()));
        return listingDto;
    }

    public static CollectionNFTDto convertToDto(CollectionNFT collectionNFT) {
        if(collectionNFT == null) {
            throw new IllegalArgumentException("Collection does not exist");
        }
        CollectionNFTDto collectionNFTDto = new CollectionNFTDto(collectionNFT.getCollectionID(), collectionNFT.getTitle());
        List<ListingDto> listings = new ArrayList<>();
        for(Listing l : collectionNFT.getListings()) {
            listings.add(convertToDto(l));
        }
        collectionNFTDto.setListings(listings);
        return collectionNFTDto;
    }


    public static NotificationDto convertToDto(Notification notification) {
        if(notification == null) {
            throw new IllegalArgumentException("Notification does not exist");
        }

       return  new NotificationDto(notification.getId(), notification.getType(), notification.getUser(), notification.getTradeOffer(), notification.getListing());
    }
}
