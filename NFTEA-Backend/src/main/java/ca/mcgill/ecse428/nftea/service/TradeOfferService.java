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
import java.util.List;

@Service
public class TradeOfferService {

    @Autowired
    TradeOfferRepository tradeServiceRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    ListingRepository listingRepository;

    @Autowired
    NotificationRepository notificationRepository;



    @Transactional
    public TradeOffer createTradeOffer(Long senderID, Long receiverID, Long listingID, Long price, String senderAddress) throws IllegalArgumentException {
        //verify that trade offer is valid
        if (userAccountRepository.findUserAccountById(senderID) == null) throw new IllegalArgumentException("Invalid senderID");
        if (senderAddress == null || senderAddress.equals("")) throw new IllegalArgumentException("Invalid sender address");

        UserAccount receiver = userAccountRepository.findUserAccountById(receiverID);
        if (receiver == null) throw new IllegalArgumentException("Invalid receiverID");

        Listing listing = listingRepository.findListingByListingID(listingID);
        if (listing == null) throw new IllegalArgumentException("Invalid listingID");
        if (price < 0) throw new IllegalArgumentException("Price cannot be less than zero!");
        //create trade
        TradeOffer myTrade = new TradeOffer(senderID,receiverID,listing,price, senderAddress);
        myTrade.setOnGoing(true);
        tradeServiceRepository.save(myTrade); //save trade

        // Create trade notification
        Notification notification = new Notification(Notification.NotificationType.TRADE_OFFER, receiver, myTrade, listing);
        notificationRepository.save(notification);

        return myTrade; //return
    }

    @Transactional
    public TradeOffer acceptTradeOffer(Long id) throws IllegalArgumentException {
        //get current trade offer
        TradeOffer myTrade = tradeServiceRepository.findTradeOfferById(id);

        //verify that trade offer is valid
        if (myTrade == null) throw new IllegalArgumentException("Invalid TradeOffer");

        //check status of the offer
        if (!myTrade.isAccepted() && !myTrade.isDeclined() && myTrade.isOnGoing()) { //check status available -> initially each offer must have accepted -> false; declined -> false;
            //change status of trade
            myTrade.setAccepted(true);
            myTrade.setOnGoing(false);
            myTrade.setDeclined(false);

            tradeServiceRepository.save(myTrade); //save trade
            notificationRepository.deleteByListing(myTrade.getListing()); // delete listing & notification(s)

            return myTrade;
        }
        else { //unavailable
            if (myTrade.isAccepted()) throw new IllegalArgumentException("TradeOffer already accepted");
            else throw new IllegalArgumentException("TradeOffer already declined");
        }
    }

    @Transactional
    public List<TradeOffer> getTradeOfferByOnGoingAndSenderID(boolean onGoing, long senderID){
        List<TradeOffer> tradeOffers = new ArrayList<>();
        for (TradeOffer t:tradeServiceRepository.findAllByOnGoingAndSenderID(true, senderID)) {
            tradeOffers.add(t);
        }
        return tradeOffers;
    }
    @Transactional
    public List<TradeOffer> getTradeOfferByOnGoingAndReceiverID(boolean onGoing, long receiverID){
        List<TradeOffer> tradeOffers = new ArrayList<>();
        for (TradeOffer t:tradeServiceRepository.findAllByOnGoingAndReceiverID(true, receiverID)) {
            tradeOffers.add(t);
        }
        return tradeOffers;
    }
    @Transactional
    public List<TradeOffer> getTradeOfferByAcceptedAndSenderID(boolean accepted, long senderID){
        List<TradeOffer> tradeOffers = new ArrayList<>();
        for (TradeOffer t:tradeServiceRepository.findAllByAcceptedAndSenderID(true, senderID)) {
            tradeOffers.add(t);
        }
        return tradeOffers;
    }
    @Transactional
    public List<TradeOffer> getTradeOfferByAcceptedAndReceiverID(boolean accepted, long receiverID){
        List<TradeOffer> tradeOffers = new ArrayList<>();
        for (TradeOffer t:tradeServiceRepository.findAllByAcceptedAndReceiverID(true, receiverID)) {
            tradeOffers.add(t);
        }
        return tradeOffers;
    }
    @Transactional
    public List<TradeOffer> getTradeOfferByDeclinedAndReceiverID(boolean declined, long receiverID){
        List<TradeOffer> tradeOffers = new ArrayList<>();
        for (TradeOffer t:tradeServiceRepository.findAllByDeclinedAndReceiverID(true, receiverID)) {
            tradeOffers.add(t);
        }
        return tradeOffers;
    }


    @Transactional
    public void clear() {
        tradeServiceRepository.deleteAll();
    }

    @Transactional
    public TradeOffer declineTradeOffer(Long id) throws IllegalArgumentException {
        //get current trade offer
        TradeOffer myTrade = tradeServiceRepository.findTradeOfferById(id);

        //verify that trade offer is valid
        if (myTrade == null) throw new IllegalArgumentException("Invalid TradeOffer");

        //check status of the offer
        if (!myTrade.isAccepted() && !myTrade.isDeclined() && myTrade.isOnGoing()) { //check status available -> initially each offer must have accepted -> false; declined -> false;
            //change status of trade
            myTrade.setAccepted(false);
            myTrade.setOnGoing(false);
            myTrade.setDeclined(true);

            tradeServiceRepository.save(myTrade); //save trade
            notificationRepository.deleteByListing(myTrade.getListing());
            return myTrade;
        } else { //unavailable
            if (myTrade.isAccepted()) throw new IllegalArgumentException("TradeOffer already accepted");
            else throw new IllegalArgumentException("TradeOffer already declined");
        }
    }
}
