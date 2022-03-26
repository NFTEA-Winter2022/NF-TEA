package ca.mcgill.ecse428.nftea.dao;

import ca.mcgill.ecse428.nftea.model.TradeOffer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TradeOfferRepository extends CrudRepository<TradeOffer,Long> {
    TradeOffer findTradeOfferById(long id);
    List<TradeOffer> findAllBySenderID(long senderID);
    List<TradeOffer> findAllByOnGoingAndSenderID(boolean onGoing, long senderID);
    List<TradeOffer> findAllByOnGoingAndReceiverID(boolean onGoing, long receiverID);
    List<TradeOffer> findAllByAcceptedAndSenderID(boolean accepted, long senderID);
    List<TradeOffer> findAllByAcceptedAndReceiverID(boolean accepted, long receiverID);
    List<TradeOffer> findAllByReceiverID(long receiverID);
}
