package ca.mcgill.ecse428.nftea.service;

import ca.mcgill.ecse428.nftea.dao.ListingRepository;
import ca.mcgill.ecse428.nftea.dao.NotificationRepository;
import ca.mcgill.ecse428.nftea.dao.TradeOfferRepository;
import ca.mcgill.ecse428.nftea.dao.UserAccountRepository;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.Notification;
import ca.mcgill.ecse428.nftea.model.TradeOffer;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    TradeOfferRepository tradeOfferRepository;

    @Autowired
    ListingRepository listingRepository;

    @Transactional
    public ArrayList<Notification> getNotifications(Long userId) throws IllegalArgumentException {
        //verify that user exists is valid
        if (userAccountRepository.findUserAccountById(userId) == null) throw new IllegalArgumentException("Invalid userId");

        return notificationRepository.findNotificationByUserId(userId);
    }

    @Transactional
    public Notification createNotificationListingSale(Long userId, Long tradeOfferId, Long listingId) throws IllegalArgumentException {
        UserAccount user = userAccountRepository.findUserAccountById(userId);
        if (user == null) throw new IllegalArgumentException("Invalid userId");
        TradeOffer trade = tradeOfferRepository.findTradeOfferById(tradeOfferId);
        if (trade == null) throw new IllegalArgumentException("Invalid tradeId");
        Listing list = listingRepository.findListingByListingID(listingId);
        if (list == null) throw new IllegalArgumentException("Invalid listingId");
        Notification notification = new Notification(Notification.NotificationType.LISTING_SALE, user, trade, list);
        notificationRepository.save(notification);
        return notification;
    }

    @Transactional
    public Notification createNotificationTRADE_OFFER(Long userId, Long tradeOfferId, Long listingId) throws IllegalArgumentException {
        UserAccount user = userAccountRepository.findUserAccountById(userId);
        if (user == null) throw new IllegalArgumentException("Invalid userId");
        TradeOffer trade = tradeOfferRepository.findTradeOfferById(tradeOfferId);
        if (trade == null) throw new IllegalArgumentException("Invalid tradeId");
        Listing list = listingRepository.findListingByListingID(listingId);
        if (list == null) throw new IllegalArgumentException("Invalid listingId");
        Notification notification = new Notification(Notification.NotificationType.TRADE_OFFER, user, trade, list);
        notificationRepository.save(notification);
        return notification;
    }


}
