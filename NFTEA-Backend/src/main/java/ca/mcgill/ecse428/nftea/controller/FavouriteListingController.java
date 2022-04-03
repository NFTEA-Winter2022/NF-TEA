package ca.mcgill.ecse428.nftea.controller;

import ca.mcgill.ecse428.nftea.dao.FavouriteListingRepository;
import ca.mcgill.ecse428.nftea.dto.FavouriteListingDto;
import ca.mcgill.ecse428.nftea.dto.ListingDto;
import ca.mcgill.ecse428.nftea.dto.UserAccountDto;
import ca.mcgill.ecse428.nftea.model.FavouriteListing;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
import ca.mcgill.ecse428.nftea.service.FavouriteListingService;
import ca.mcgill.ecse428.nftea.service.ListingService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import ca.mcgill.ecse428.nftea.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
public class FavouriteListingController {
    @Autowired
    FavouriteListingRepository favouriteListingRepository;

    @Autowired
    FavouriteListingService favouriteListingService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private ListingService listingService;


    @PostMapping(value = {"/UserProfilePage/createFavourite", "/UserProfilePage/createFavourite/"})
    public ResponseEntity createFavourite(
            @RequestParam Long userid,
            @RequestParam Long listingid
    ) {
        UserAccount user;
        Listing listing;
        FavouriteListing favourite;
        try {
            user = userAccountService.getAccount(userid);
            listing = listingService.getListingByID(listingid);
//            if (favouriteListingService.getFavouriteByUserAndListing(userid,listingid).equals(null)){
//                throw new IllegalArgumentException();
//            }
            favourite = favouriteListingService.createFavourite(user, listing);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(FavouriteToDto(favourite), HttpStatus.OK);

    }

    @GetMapping(value = {"/UserProfilePage/getFavourites", "/UserProfilePage/getFavourites/"})
    public ResponseEntity getAllFavs(
            @RequestParam long userid
    ) {
        UserAccount user;
        Listing listing;
        FavouriteListing favourite;
        ArrayList<FavouriteListing> favourites;
        try {
            favourites = favouriteListingService.getALLFavouriteListings(userid);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(FavouriteToDto(favourites), HttpStatus.OK);

    }

    @GetMapping(value = {"/UserProfilePage/getFavourite", "/UserProfilePage/getFavourite/"})
    public ResponseEntity getAllFavs(
            @RequestParam long userid,
            @RequestParam long listingid
    ) {
        FavouriteListing favourite;
        try {
            favourite = favouriteListingService.getFavouriteByUserAndListing(userid,listingid);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(FavouriteToDto(favourite), HttpStatus.OK);

    }




    @DeleteMapping(value = {"/UserProfilePage/delFavouriteByUserAndListing", "/UserProfilePage/delFavouriteByUserAndListing/"})
    public ResponseEntity delFavourite(
            @RequestParam long userid,
            @RequestParam long listingid
    )

     {
        UserAccount user;
        Listing listing;
        FavouriteListing favourite;
        try {
            favourite = favouriteListingService.deleteByUserAndListing(userid,listingid);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(FavouriteToDto(favourite), HttpStatus.OK);

    }

    @DeleteMapping(value = {"/UserProfilePage/delFavourite", "/UserProfilePage/delFavourite/"})
    public ResponseEntity delFavourite(
            @RequestParam long favouriteid
    )

    {
        UserAccount user;
        Listing listing;
        FavouriteListing favourite;
        try {
            favourite = favouriteListingService.deleteFavourite(favouriteid);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(FavouriteToDto(favourite), HttpStatus.OK);

    }

    private FavouriteListingDto FavouriteToDto(FavouriteListing listing) {
        ListingDto ldto = DtoUtils.convertToDto(listing.getListing());
        UserAccountDto udto = DtoUtils.convertToDto(listing.getUser());
        FavouriteListingDto myfav = new FavouriteListingDto(udto, ldto);
        return myfav;
    }

    private ArrayList<FavouriteListingDto> FavouriteToDto(ArrayList<FavouriteListing> list) {
        ArrayList<FavouriteListingDto> favourite = new ArrayList<>();
        for (FavouriteListing l : list) {
            ListingDto ldto = DtoUtils.convertToDto(l.getListing());
            UserAccountDto udto = DtoUtils.convertToDto(l.getUser());
            FavouriteListingDto myfav = new FavouriteListingDto(udto, ldto);
            favourite.add(myfav);
        }
        return favourite;
    }

}
