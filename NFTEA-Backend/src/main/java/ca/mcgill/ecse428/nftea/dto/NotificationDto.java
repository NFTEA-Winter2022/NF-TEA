package ca.mcgill.ecse428.nftea.dto;

import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.Notification.NotificationType;
import ca.mcgill.ecse428.nftea.model.TradeOffer;
import ca.mcgill.ecse428.nftea.model.UserAccount;

public class NotificationDto {
    private Long id;
    private NotificationType type;
    private UserAccount user;
    private TradeOffer tradeOffer;
    private Listing listing;

    public NotificationDto(Long id, NotificationType type, UserAccount user, TradeOffer tradeOffer, Listing listing)
    {
        this.id = id;
        this.type = type;
        this.user = user;
        this.tradeOffer = tradeOffer;
        this.listing = listing;
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
}
