package ca.mcgill.ecse428.nftea.model;

import java.util.*;
import javax.persistence.*;

@Entity
public class TradeOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long senderID;
    private Long receiverID;
    private String senderAddress;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Listing listing;
    private boolean onGoing;
    private boolean accepted;
    private boolean declined;

    private Long price;


    //------------------------
    // CONSTRUCTOR
    //------------------------
    public TradeOffer() {}

    public TradeOffer(Long senderID, Long receiverID, Listing listing, Long price, String senderAddress) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.listing = listing;
        this.price = price;
        this.onGoing = false;
        this.accepted = false;
        this.declined = false;
        this.senderAddress = senderAddress;
    }

    //------------------------
    // Getters and Setters
    //------------------------

    public Long getId() {
        return id;
    }

    public boolean isOnGoing() {
        return onGoing;
    }

    public void setOnGoing(boolean onGoing) {
        this.onGoing = onGoing;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isDeclined() {
        return declined;
    }

    public void setDeclined(boolean declined) {
        this.declined = declined;
    }

    public Long getSenderID() {
        return senderID;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderID(Long senderID) {
        this.senderID = senderID;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public Long getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Long receiverID) {
        this.receiverID = receiverID;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }


}
