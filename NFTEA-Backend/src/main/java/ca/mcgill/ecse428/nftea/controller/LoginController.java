package ca.mcgill.ecse428.nftea.controller;


import ca.mcgill.ecse428.nftea.dto.UserAccountDto;
import ca.mcgill.ecse428.nftea.exception.WrongInputException;
import ca.mcgill.ecse428.nftea.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse428.nftea.utils.DtoUtils;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping(value = {"/home/login", "/home/login"})
    public ResponseEntity loginUserAccount(@RequestParam("email") String email, @RequestParam("password") String password){
        try {
            return new ResponseEntity<>(DtoUtils.convertToDto(loginService.loginUserAccount(email, password)), HttpStatus.OK);
        } catch (WrongInputException e) {
            return new ResponseEntity(e.getMessage() ,e.getHttpStatus());
        }
    }
}
