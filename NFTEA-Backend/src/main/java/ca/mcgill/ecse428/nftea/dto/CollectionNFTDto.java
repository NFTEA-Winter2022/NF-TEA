package ca.mcgill.ecse428.nftea.dto;

import ca.mcgill.ecse428.nftea.model.CollectionNFT;
import ca.mcgill.ecse428.nftea.model.Listing;

import java.util.List;

public class CollectionNFTDto {

    private Long collectionID;
    private String title;
    private List<ListingDto> listings;

    public CollectionNFTDto() {

    }

    public CollectionNFTDto(long collectionID, String title) {
        this.collectionID = collectionID;
        this.title = title;
    }

    public Long getCollectionID() {
        return collectionID;
    }

    public String getTitle() {
        return title;
    }

    public List<ListingDto> getListings() {
        return listings;
    }

    public void setListings(List<ListingDto> listings) {
        this.listings = listings;
    }
}
