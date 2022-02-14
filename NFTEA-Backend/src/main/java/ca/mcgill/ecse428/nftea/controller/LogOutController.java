package ca.mcgill.ecse428.nftea.controller;

import ca.mcgill.ecse428.nftea.dto.UserAccountDto;
import ca.mcgill.ecse428.nftea.service.LogOutService;
import ca.mcgill.ecse428.nftea.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class LogOutController {
    @Autowired
    private LogOutService logoutservice;
    @PutMapping(value = { "/user/logout/{id}", "/user/logout/{id}/"})
    public ResponseEntity logoutID(@PathVariable("id") String id) {
        boolean b;
        try {
            b = logoutservice.LogOutAccount(Long.parseLong(id));
        } catch (IllegalArgumentException | NullPointerException msg) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg.getMessage());
        }
        if (b) return ResponseEntity.status(HttpStatus.OK).body("Logged out");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error: Did not log out");
    }

}
