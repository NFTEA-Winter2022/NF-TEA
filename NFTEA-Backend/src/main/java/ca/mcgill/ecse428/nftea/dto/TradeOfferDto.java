package ca.mcgill.ecse428.nftea.dto;

import ca.mcgill.ecse428.nftea.model.Listing;

public class TradeOfferDto {

    private Long id;
    private Long senderID;
    private Long receiverID;
    private String senderAddress;
    private Listing listing;
    private boolean onGoing;
    private boolean accepted;
    private boolean declined;

    private Long price;


    //------------------------
    // CONSTRUCTOR
    //------------------------
    public TradeOfferDto() {}


    public TradeOfferDto(Long id, Long senderID, Long receiverID, Listing listing, Long price, String senderAddress) {
        this.id = id;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.listing = listing;
        this.price = price;
        this.onGoing = false;
        this.accepted = false;
        this.declined = false;
        this.senderAddress = senderAddress;
    }


    public Long getId() {
        return id;
    }

    public Long getSenderID() {
        return senderID;
    }

    public Long getReceiverID() {
        return receiverID;
    }

    public Listing getListing() {
        return listing;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public boolean isOnGoing() {
        return onGoing;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public boolean isDeclined() {
        return declined;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
