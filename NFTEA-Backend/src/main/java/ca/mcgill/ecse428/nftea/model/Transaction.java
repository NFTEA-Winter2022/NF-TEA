package ca.mcgill.ecse428.nftea.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UserAccount buyer;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UserAccount seller;
    private String title;
    private Long price;
    private String nftLink;
    private Long discountedPrice;
    private LocalDateTime transactionTime;

    public Transaction(){
        this.transactionTime = LocalDateTime.now();
    }
    public Transaction(UserAccount buyer, UserAccount seller, Listing listing) {
        this.buyer = buyer;
        this.seller = seller;
        this.title = listing.getTitle();
        this.price = listing.getPrice();
        this.nftLink = listing.getNftLink();
        this.discountedPrice = listing.getDiscountedPrice();
        this.transactionTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public UserAccount getBuyer() {
        return buyer;
    }

    public void setBuyer(UserAccount buyer) {
        this.buyer = buyer;
    }

    public UserAccount getSeller() {
        return seller;
    }

    public void setSeller(UserAccount seller) {
        this.seller = seller;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getNftLink() {
        return nftLink;
    }

    public void setNftLink(String nftLink) {
        this.nftLink = nftLink;
    }

    public Long getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Long discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
}
