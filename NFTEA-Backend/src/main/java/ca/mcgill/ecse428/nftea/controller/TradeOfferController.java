package ca.mcgill.ecse428.nftea.controller;

import ca.mcgill.ecse428.nftea.dto.ListingDto;
import ca.mcgill.ecse428.nftea.dto.TradeOfferDto;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.TradeOffer;
import ca.mcgill.ecse428.nftea.service.TradeOfferService;
import ca.mcgill.ecse428.nftea.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping(value = {"/Market/onGoingReceiver", "/Market/onGoingReceiver/"})
    public ResponseEntity onGoingReceiver(@RequestParam Long receiverID){
        List<TradeOffer> tradeOffers = new ArrayList<>();
        List<TradeOfferDto> tradeOfferDtos = new ArrayList<>();
        try{
            tradeOffers = tradeOfferService.getTradeOfferByOnGoingAndReceiverID(true, receiverID);
            for (TradeOffer t : tradeOffers){
                tradeOfferDtos.add(convertDto(t));
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(tradeOfferDtos, HttpStatus.OK);

    }
    @GetMapping(value = {"/Market/onGoingSender", "/Market/onGoingSender/"})
    public ResponseEntity onGoingSender(@RequestParam Long senderID){
        List<TradeOffer> tradeOffers = new ArrayList<>();
        List<TradeOfferDto> tradeOfferDtos = new ArrayList<>();
        try{
            tradeOffers = tradeOfferService.getTradeOfferByOnGoingAndSenderID(true, senderID);
            for (TradeOffer t : tradeOffers){
                tradeOfferDtos.add(convertDto(t));
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(tradeOfferDtos, HttpStatus.OK);

    }
    @GetMapping(value = {"/Market/acceptedSender", "/Market/acceptedSender/"})
    public ResponseEntity acceptedSender(@RequestParam Long senderID){
        List<TradeOffer> tradeOffers = new ArrayList<>();
        List<TradeOfferDto> tradeOfferDtos = new ArrayList<>();
        try{
            tradeOffers = tradeOfferService.getTradeOfferByAcceptedAndSenderID(true, senderID);
            for (TradeOffer t : tradeOffers){
                tradeOfferDtos.add(convertDto(t));
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(tradeOfferDtos, HttpStatus.OK);
    }
    @GetMapping(value = {"/Market/acceptedReceiver", "/Market/acceptedReceiver/"})
    public ResponseEntity acceptedReceiver(@RequestParam Long receiverID){
        List<TradeOffer> tradeOffers = new ArrayList<>();
        List<TradeOfferDto> tradeOfferDtos = new ArrayList<>();
        try{
            tradeOffers = tradeOfferService.getTradeOfferByAcceptedAndReceiverID(true, receiverID);
            for (TradeOffer t : tradeOffers){
                tradeOfferDtos.add(convertDto(t));
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(tradeOfferDtos, HttpStatus.OK);
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
