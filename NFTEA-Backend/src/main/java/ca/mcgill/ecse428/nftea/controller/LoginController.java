package ca.mcgill.ecse428.nftea.controller;


import ca.mcgill.ecse428.nftea.dto.UserAccountDto;
import ca.mcgill.ecse428.nftea.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse428.nftea.utils.DtoUtils;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PatchMapping(value = {"/home/login", "/home/login/"})
    public UserAccountDto loginUserAccount(@RequestParam("email") String email, @RequestParam("password") String password) throws IllegalArgumentException{
        return DtoUtils.convertToDto(loginService.loginUserAccount(email, password));
    }
}
