package ca.mcgill.ecse428.nftea.model;

import javax.persistence.*;

@Entity
public class Notification {
    //------------------------
    // ENUMERATIONS
    //------------------------

    public enum NotificationType {
        TRADE_OFFER,
        LISTING_SALE
    }

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //Listing Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private NotificationType type;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UserAccount user;
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private TradeOffer tradeOffer;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Listing listing;

    //------------------------
    // CONSTRUCTOR
    //------------------------
    public Notification(){}

    public Notification(NotificationType type, UserAccount user, TradeOffer tradeOffer, Listing listing)
    {
        this.type = type;
        this.user = user;
        this.tradeOffer = tradeOffer;
        this.listing = listing;
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setId(Long id)
    {
        boolean wasSet = false;
        id = id;
        wasSet = true;
        return wasSet;
    }

    public boolean setType(NotificationType type)
    {
        boolean wasSet = false;
        type = type;
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

    public boolean setTradeOffer(TradeOffer tradeOffer)
    {
        boolean wasSet = false;
        this.tradeOffer = tradeOffer;
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

    public NotificationType getType()
    {
        return type;
    }

    public UserAccount getUser()
    {
        return user;
    }

    public TradeOffer getTradeOffer()
    {
        return tradeOffer;
    }

    public Listing getListing()
    {
        return listing;
    }
    public void delete()
    {}


    public String toString()
    {
        String tradeOffer = "";
        if (this.tradeOffer != null) {
            tradeOffer = this.tradeOffer.getId().toString();
        }

        return super.toString() + "["+
                "id" + ":" + getId()+ "," +
                "type" + ":" + getType()+ "," +
                "user" + ":" + getUser().getUsername() + "," +
                "tradeOffer" + ":" + tradeOffer + "," +
                "listing" + ":" + getListing().getListingID()+
                "]";
    }
}
