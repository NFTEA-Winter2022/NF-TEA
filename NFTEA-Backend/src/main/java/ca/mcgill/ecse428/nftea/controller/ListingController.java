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


}
