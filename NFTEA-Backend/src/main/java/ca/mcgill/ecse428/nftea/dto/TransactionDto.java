package ca.mcgill.ecse428.nftea.dto;

import java.time.LocalDateTime;

public class TransactionDto {
    private Long id;
    private UserAccountDto buyer;
    private UserAccountDto seller;
    private String title;
    private Long price;
    private String nftLink;
    private Long discountedPrice;
    private LocalDateTime transactionTime;

    public TransactionDto() {
    }

    public TransactionDto(Long id, UserAccountDto buyer, UserAccountDto seller, String title, Long price, String nftLink, Long discountedPrice, LocalDateTime transactionTime) {
        this.id = id;
        this.buyer = buyer;
        this.seller = seller;
        this.title = title;
        this.price = price;
        this.nftLink = nftLink;
        this.discountedPrice = discountedPrice;
        this.transactionTime = transactionTime;
    }

    public Long getId() {
        return id;
    }

    public UserAccountDto getBuyer() {
        return buyer;
    }

    public UserAccountDto getSeller() {
        return seller;
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

    public Long getDiscountedPrice() {
        return discountedPrice;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setBuyer(UserAccountDto buyer) {
        this.buyer = buyer;
    }

    public void setSeller(UserAccountDto seller) {
        this.seller = seller;
    }

}
