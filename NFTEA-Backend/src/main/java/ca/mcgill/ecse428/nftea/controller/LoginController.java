package ca.mcgill.ecse428.nftea.controller;


import ca.mcgill.ecse428.nftea.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

//    @GetMapping(value = {"/home/login", "/home/login"})
//    public
}
