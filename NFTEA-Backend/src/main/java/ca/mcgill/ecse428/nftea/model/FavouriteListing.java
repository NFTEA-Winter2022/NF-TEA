package ca.mcgill.ecse428.nftea.model;

import javax.persistence.*;

@Entity
public class FavouriteListing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UserAccount user;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Listing listing;

    public FavouriteListing(UserAccount user, Listing listing)
    {
        this.user = user;
        this.listing = listing;
    }

    public FavouriteListing() {

    }

    public boolean setId(Long id)
    {
        boolean wasSet = false;
        id = id;
        wasSet = true;
        return wasSet;
    }
    public boolean setUser(UserAccount user)
    {
        boolean wasSet = false;
        this.user = user;
        wasSet = true;
        return wasSet;
    }
    public boolean seListing(Listing listing)
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
    public UserAccount getUser()
    {
        return user;
    }
    public Listing getListing()
    {
        return listing;
    }

    public String toString()
    {
        return super.toString() + "["+
                "FavouriteListing" + ":" + getId()+ "," +
                "user" + ":" + getUser()+ "," +
                "listing" + ":" + getListing() + ",";
    }

}
