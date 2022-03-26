package ca.mcgill.ecse428.nftea.controller;

import ca.mcgill.ecse428.nftea.dto.TradeOfferDto;
import ca.mcgill.ecse428.nftea.model.TradeOffer;
import ca.mcgill.ecse428.nftea.service.TradeOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TradeOfferController {

    @Autowired
    private TradeOfferService tradeOfferService;

    @PostMapping(value = {"/Market/createTradeOffer", "/Market/createTradeOffer/"})
    public ResponseEntity CreateTradeOffer(@RequestParam Long senderID, @RequestParam Long receiverID, @RequestParam Long listingID, @RequestParam Long price) {
        TradeOffer myTrade;
        try {
            myTrade = tradeOfferService.createTradeOffer(senderID,receiverID,listingID, price);
        } catch (Exception msg) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg.getMessage());
        }
        return new ResponseEntity<>(convertDto(myTrade), HttpStatus.OK);
    }

    @PutMapping(value = {"/Market/acceptTradeOffer", "/Market/acceptTradeOffer/"})
    public ResponseEntity AcceptTradeOffer(@RequestParam Long id) {
        TradeOffer myTrade;
        try {
            myTrade = tradeOfferService.acceptTradeOffer(id);
        } catch (Exception msg) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg.getMessage());
        }
        return new ResponseEntity<>(convertDto(myTrade), HttpStatus.OK);
    }
    @GetMapping(value = {"/Market/onGoingSender", "/Market/onGoingSender/"})
    public ResponseEntity onGoingSender(@RequestParam Long senderID){
        List<TradeOffer>
    }

    private TradeOfferDto convertDto(TradeOffer tradeOffer) {
        return new TradeOfferDto(
                tradeOffer.getSenderID(),
                tradeOffer.getReceiverID(),
                tradeOffer.getListingID(),
                tradeOffer.getPrice()
        );
    }

}
