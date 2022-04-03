package ca.mcgill.ecse428.nftea.dto;

import javax.persistence.*;

public class FavouriteListingDto {


    private Long id;

    private UserAccountDto user;

    private ListingDto listing;

    public FavouriteListingDto(UserAccountDto user, ListingDto listing)
    {
        this.user = user;
        this.listing = listing;
    }
    public boolean setId(Long id)
    {
        boolean wasSet = false;
        id = id;
        wasSet = true;
        return wasSet;
    }
    public boolean setUser(UserAccountDto user)
    {
        boolean wasSet = false;
        this.user = user;
        wasSet = true;
        return wasSet;
    }
    public boolean seListing(ListingDto listing)
    {
        boolean wasSet = false;
        this.listing = listing;
        wasSet = true;
        return wasSet;
    }
    public Long getId()
    {
        return id;
    }
    public UserAccountDto getUser()
    {
        return user;
    }
    public ListingDto getListing()
    {
        return listing;
    }


}
