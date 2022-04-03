package ca.mcgill.ecse428.nftea.dto;

import ca.mcgill.ecse428.nftea.model.UserAccount;

public class ListingDto {
    private Long listingID;
    private String title;
    private Long price;
    private String nftLink;
    private UserAccountDto owner;

    private Long percentDiscount;
    private Long discountedPrice;
    private boolean isPriceDiscounted;

    public ListingDto() {}

    public ListingDto(
            Long listingID,
            String title,
            Long price,
            String nftLink,
            Long percentDiscount,
            Long discountedPrice,
            boolean isPriceDiscounted
    ) {
        this.listingID = listingID;
        this.title = title;
        this.price = price;
        this.nftLink = nftLink;
        this.percentDiscount = percentDiscount;
        this.discountedPrice = discountedPrice;
        this.isPriceDiscounted = isPriceDiscounted;
    }

    public Long getListingID() {
        return listingID;
    }

    public String getTitle() {
        return title;
    }

    public Long getPrice() {
        return price;
    }

    public String getNftLink() {
        return nftLink;
    }

    public UserAccountDto getOwner() {
        return owner;
    }

    public void setOwner(UserAccountDto owner) {
        this.owner = owner;
    }
}
