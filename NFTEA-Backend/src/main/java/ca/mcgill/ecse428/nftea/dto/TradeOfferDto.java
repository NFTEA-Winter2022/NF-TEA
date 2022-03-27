package ca.mcgill.ecse428.nftea.dto;

public class TradeOfferDto {

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
    public TradeOfferDto() {}


    public TradeOfferDto(Long id, Long senderID, Long receiverID, Long listingID, Long price) {
        this.id = id;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.listingID = listingID;
        this.price = price;
        this.onGoing = false;
        this.accepted = false;
        this.declined = false;
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

    public Long getListingID() {
        return listingID;
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
