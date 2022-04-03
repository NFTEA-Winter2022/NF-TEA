package ca.mcgill.ecse428.nftea.controller;

import ca.mcgill.ecse428.nftea.dao.ListingRepository;
import ca.mcgill.ecse428.nftea.dto.ListingDto;
import ca.mcgill.ecse428.nftea.model.Listing;
import ca.mcgill.ecse428.nftea.model.UserAccount;
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
public class ListingController {

    @Autowired
    ListingRepository listingRepository;
    @Autowired
    private ListingService listingService;
    @Autowired
    private UserAccountService userAccountService;

    @PostMapping(value = {"/UserProfilePage/createListing", "/UserProfilePage/createListing/"})
    public ResponseEntity createListingFromNFT(
            @RequestParam String userid,
            @RequestParam String title,
            @RequestParam String nftLink,
            @RequestParam Long price
    ) {

        UserAccount user;
        Listing listing;
        try {
            user = userAccountService.getAccount(Long.parseLong(userid));
            listing = listingService.createInitialListing(title, price, nftLink, user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(DtoUtils.convertToDto(listing), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/UserProfilePage/deleteListing", "/UserProfilePage/deleteListing/"})
    public ResponseEntity deleteListing(@RequestParam(required=false) Long listingId) {
        boolean result;
        try {
            result = listingService.deleteListing(listingId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        if (result) return ResponseEntity.status(HttpStatus.OK).body("Listing successfully deleted!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not delete listing!");
    }

    @PutMapping(value = {"/UserProfilePage/editListingTitle", "/UserProfilePage/editListingTitle/"})
    public ResponseEntity editListingTitle(
            @RequestParam Long listingId,
            @RequestParam String title) {
        Listing listing;
        try {
            listing = listingService.editTitle(listingId, title);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(DtoUtils.convertToDto(listing), HttpStatus.OK);
    }

    @PutMapping(value = {"/UserProfilePage/editListingPrice", "/UserProfilePage/editListingPrice/"})
    public ResponseEntity editListingPrice(
            @RequestParam Long listingId,
            @RequestParam Long price) {

        Listing listing;
        try {
            listing = listingService.editPrice(listingId, price);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(DtoUtils.convertToDto(listing), HttpStatus.OK);
    }

    @PutMapping(value = {"/UserProfilePage/editListingNftLink", "/UserProfilePage/editListingNftLink/"})
    public ResponseEntity editListingNftLink(
            @RequestParam Long listingId,
            @RequestParam String nftLink) {

        Listing listing;
        try {
            listing = listingService.editNftLink(listingId, nftLink);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(DtoUtils.convertToDto(listing), HttpStatus.OK);
    }

    @GetMapping(value = {"/UserProfilePage/getListing", "/UserProfilePage/getListing/"})
    public ResponseEntity getListings() {
        ArrayList<Listing> listings = new ArrayList<>();
        ArrayList<ListingDto> listingDto = new ArrayList<>();
        try {
            listings = listingService.getListings();
            for (Listing listing : listings) {
                listingDto.add(DtoUtils.convertToDto(listing));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(listingDto, HttpStatus.OK);
    }

    @GetMapping(value = {"/UserProfilePage/getListingByTitle", "/UserProfilePage/getListingByTitle/"})
    public ResponseEntity GetListingsByTitle(@RequestParam String title) {
        ArrayList<Listing> listings = new ArrayList<>();
        ArrayList<ListingDto> listingDto = new ArrayList<>();
        try {
            listings = listingService.getListingsByTitle(title);
            for (Listing listing : listings) {
                listingDto.add(DtoUtils.convertToDto(listing));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(listingDto, HttpStatus.OK);
    }

    @GetMapping(value = {"/UserProfilePage/getListingByPrice", "/UserProfilePage/getListingByPrice/"})
    public ResponseEntity GetListingsByPrice(@RequestParam Long price) {
        ArrayList<Listing> listings = new ArrayList<>();
        ArrayList<ListingDto> listingDto = new ArrayList<>();
        try {
            listings = listingService.getListingsByPrice(price);
            for (Listing listing : listings) {
                listingDto.add(DtoUtils.convertToDto(listing));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(listingDto, HttpStatus.OK);
    }

    @GetMapping(value = {"/UserProfilePage/getListingByNftLink", "/UserProfilePage/getListingByNftLink/"})
    public ResponseEntity GetListingsByNftLink(@RequestParam String NftLink) {
        ArrayList<Listing> listings = new ArrayList<>();
        ArrayList<ListingDto> listingDto = new ArrayList<>();
        try {
            listings = listingService.getListingsByNFTLink(NftLink);
            for (Listing listing : listings) {
                listingDto.add(DtoUtils.convertToDto(listing));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(listingDto, HttpStatus.OK);
    }


    @GetMapping(value = "/UserProfilePage/getMyListings")
    public ResponseEntity GetMyListings(@RequestParam Long id) {
        ArrayList<ListingDto> listingDto = new ArrayList<>();
        ArrayList<Listing> listing;
        try {
            UserAccount user = userAccountService.getAccount(id);
            listing = listingRepository.findListingByOwner(user);
            for (Listing l : listing) {
                listingDto.add(DtoUtils.convertToDto(l));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(listingDto, HttpStatus.OK);
    }


    @PutMapping(value = {"/UserProfilePage/discountListingPrice", "/UserProfilePage/discountListingPrice/"})
    public ResponseEntity discountListingPrice(@RequestParam Long listingId, @RequestParam Long percent) {
        Listing listing;
        try {
            listing = listingService.discountPrice(listingId,percent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(DtoUtils.convertToDto(listing), HttpStatus.OK);
    }

    @PutMapping(value = {"/UserProfilePage/undiscountListingPrice", "/UserProfilePage/undiscountListingPrice/"})
    public ResponseEntity resetListingPrice(@RequestParam Long listingId) {
        Listing listing;
        try {
            listing = listingService.resetPrice(listingId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return new ResponseEntity<>(DtoUtils.convertToDto(listing), HttpStatus.OK);
    }



}
