package ca.mcgill.ecse428.nftea.model;

import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TradeOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long senderID;
    private Long receiverID;
    private Long listingID;
    private boolean onGoing;
    private boolean accepted;
    private boolean declined;

    private Long price;


    //------------------------
    // CONSTRUCTOR
    //------------------------
    public TradeOffer() {}

    public TradeOffer(Long senderID, Long receiverID, Long listingID, Long price) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.listingID = listingID;
        this.price = price;
        this.onGoing = false;
        this.accepted = false;
        this.declined = false;
    }

    //------------------------
    // Getters and Setters
    //------------------------

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

    public void setSenderID(Long senderID) {
        this.senderID = senderID;
    }

    public Long getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Long receiverID) {
        this.receiverID = receiverID;
    }

    public Long getListingID() {
        return listingID;
    }

    public void setListingID(Long listingID) {
        this.listingID = listingID;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }


}
