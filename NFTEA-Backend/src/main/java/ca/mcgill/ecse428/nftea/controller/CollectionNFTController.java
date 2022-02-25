package ca.mcgill.ecse428.nftea.controller;

import ca.mcgill.ecse428.nftea.dto.CollectionNFTDto;
import ca.mcgill.ecse428.nftea.model.CollectionNFT;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.service.CollectionNFTService;
import ca.mcgill.ecse428.nftea.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class CollectionNFTController {

    @Autowired
    private CollectionNFTService collectionNFTService;

    @GetMapping(value = {"/userProfile/collections", "/userProfile/collections"})
    public ResponseEntity getUserCollections(@RequestParam("userID") long userID){
        try {
            List<CollectionNFT> collections = collectionNFTService.getCollectionNFTByUser(userID);
            return new ResponseEntity<> (collections.stream().map(c -> DtoUtils.convertToDto(c)).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PatchMapping(value = {"/userProfile/collections/updateTitle", "/userProfile/collections/updateTitle/"})
    public ResponseEntity updateCollectionTitle(@RequestParam("collectionID") long collectionID, @RequestParam("title") String newTitle){
        try {
            return new ResponseEntity<> (DtoUtils.convertToDto(collectionNFTService.updateCollectionTitle(collectionID, newTitle)), HttpStatus.OK);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PatchMapping(value = {"/userProfile/collections/addListing", "/userProfile/collections/addListing/"})
    public ResponseEntity addListing(@RequestParam("collectionID") long collectionID, @RequestParam("listing") long listingID) {
        try {
            return new ResponseEntity<> (DtoUtils.convertToDto(collectionNFTService.addListing(collectionID, listingID)), HttpStatus.OK);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PatchMapping(value = {"/userProfile/collections/addListings", "/userProfile/collections/addListings/"})
    public ResponseEntity addListing(@RequestParam("collectionID") long collectionID, @RequestParam("listings") List<Long> listingIDs) {
        try {
            return new ResponseEntity<> (DtoUtils.convertToDto(collectionNFTService.addListings(collectionID, listingIDs)), HttpStatus.OK);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PatchMapping(value = {"/userProfile/collections/removeListing", "/userProfile/collections/removeListing/"})
    public ResponseEntity removeListing(@RequestParam("collectionID") long collectionID, @RequestParam("listing") long listingID) {
        try {
            return new ResponseEntity<> (DtoUtils.convertToDto(collectionNFTService.removeListing(collectionID, listingID)), HttpStatus.OK);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PatchMapping(value = {"/userProfile/collections/removeListings", "/userProfile/collections/removeListings/"})
    public ResponseEntity removeListing(@RequestParam("collectionID") long collectionID, @RequestParam("listings") List<Long> listingIDs) {
        try {
            return new ResponseEntity<> (DtoUtils.convertToDto(collectionNFTService.removeListings(collectionID, listingIDs)), HttpStatus.OK);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

}
