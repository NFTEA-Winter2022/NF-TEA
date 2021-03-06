package ca.mcgill.ecse428.nftea.utils;

import ca.mcgill.ecse428.nftea.dto.*;
import ca.mcgill.ecse428.nftea.model.*;
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
        ListingDto listingDto = new ListingDto(
                listing.getListingID(),
                listing.getTitle(),
                listing.getPrice(),
                listing.getNftLink(),
                listing.getPercentDiscount(),
                listing.getDiscountedPrice(),
                listing.isPriceDiscounted()
        );
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

    public static TransactionDto convertToDto(Transaction transaction) {
        if(transaction == null) {
            throw new IllegalArgumentException("Collection does not exist");
        }
        TransactionDto transactionDto = new TransactionDto(transaction.getId(), convertToDto(transaction.getBuyer()),
                convertToDto(transaction.getSeller()), transaction.getTitle(), transaction.getPrice(), transaction.getNftLink(),
                transaction.getDiscountedPrice(), transaction.getTransactionTime());
        return transactionDto;
    }


    public static NotificationDto convertToDto(Notification notification) {
        if(notification == null) {
            throw new IllegalArgumentException("Notification does not exist");
        }

       return  new NotificationDto(notification.getId(), notification.getType(), notification.getUser(), notification.getTradeOffer(), notification.getListing());
    }
}
