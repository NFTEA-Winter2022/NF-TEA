package ca.mcgill.ecse428.nftea.controller;

import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.ListingService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import ca.mcgill.ecse428.nftea.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class ListingController {

    @Autowired
    private ListingService listingService;

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping(value = {"/UserProfilePage/createListing", "/UserProfilePage/createListing/"})
    public ResponseEntity createListingFromNFT(
            @RequestParam String userid,
            @RequestParam String title,
            @RequestParam String nftLink) {

        UserAccount user;
        Listing listing;
        try {
            user = userAccountService.getAccount(Long.parseLong(userid));
            listing = listingService.createInitialListing(title, 0L, nftLink, user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(DtoUtils.convertToDto(listing), HttpStatus.OK);
    }

    @GetMapping(value = {"/SearchListingPage/ListingID", "/SearchListingPage/ListingID/"})
    public ResponseEntity getListingWithID(@RequestParam("listingID") Long listingID){
        try{
            return new ResponseEntity<>(DtoUtils.convertToDto(listingService.getListingWithId(listingID)), HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = {"/SearchListingPage/Title", "/SearchListingPage/Title/"})
    public ResponseEntity getListingWithTitle(@RequestParam("title") String title ){
        try{
            List<Listing> listings = listingService.getListingsWithTitle(title);
            return new ResponseEntity<>(listings.stream().map(l -> DtoUtils.convertToDto(l)).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = {"/SearchListingPage/NFTLink", "/SearchListingPage/NFTLink"})
    public ResponseEntity getListingWithNFTLink(@RequestParam("NFTLink") String NFTLink ){
        try{
            List<Listing> listings = listingService.getListingWithNFTLink(NFTLink);
            return new ResponseEntity<>(listings.stream().map(l -> DtoUtils.convertToDto(l)).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = {"/SearchListingPage/Owner", "/SearchListingPage/Owner/"})
    public ResponseEntity getListingWithOwner(@RequestParam("Owner") UserAccount Owner ){
        try{
            List<Listing> listings = listingService.getListingsWithOwner(Owner);
            return new ResponseEntity<>(listings.stream().map(l -> DtoUtils.convertToDto(l)).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
