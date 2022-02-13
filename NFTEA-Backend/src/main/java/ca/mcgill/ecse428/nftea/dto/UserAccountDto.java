package ca.mcgill.ecse428.nftea.dto;

import java.time.LocalDateTime;

public class UserAccountDto
{

    //------------------------
    // ENUMERATIONS
    //------------------------

    public enum UserRole { Admin, Customer }

    //------------------------
    // MEMBER VARIABLES
    //------------------------

    //UserAccount Attributes

    private Long numberID;
    private String firstName;
    private String lastName;
    private String userEmail;
    private String username;
    private String password;
    private boolean isLoggedIn;
    private int loginAttempts;
    private LocalDateTime lastAttempt;
    private ca.mcgill.ecse428.nftea.model.UserAccount.UserRole userRole;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public UserAccountDto(){

    }
    public UserAccountDto(Long aNumberID, String aFirstName, String aLastName, String aUserEmail, String aUsername, String aPassword, boolean aIsLoggedIn, int loginAttempts, LocalDateTime lastAttempt, ca.mcgill.ecse428.nftea.model.UserAccount.UserRole aUserRole)
    {
        numberID = aNumberID;
        firstName = aFirstName;
        lastName = aLastName;
        userEmail = aUserEmail;
        username = aUsername;
        password = aPassword;
        isLoggedIn = aIsLoggedIn;
        userRole = aUserRole;
        this.loginAttempts = loginAttempts;
		this.lastAttempt = lastAttempt;
    }

    //------------------------
    // INTERFACE
    //------------------------

    public Long getNumberID()
    {
        return numberID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public boolean getIsLoggedIn()
    {
        return isLoggedIn;
    }
    
    public int getLoginAttempts() 
    {
    	return loginAttempts;
    }
    
    public LocalDateTime getLastAttempt() 
    {
    	return lastAttempt;
    }

    public ca.mcgill.ecse428.nftea.model.UserAccount.UserRole getUserRole()
    {
        return userRole;
    }
    /* Code from template attribute_IsBoolean */
    public boolean isIsLoggedIn()
    {
        return isLoggedIn;
    }


    public String toString()
    {
        return super.toString() + "["+
                "numberID" + ":" + getNumberID()+ "," +
                "firstName" + ":" + getFirstName()+ "," +
                "lastName" + ":" + getLastName()+ "," +
                "userEmail" + ":" + getUserEmail()+ "," +
                "username" + ":" + getUsername()+ "," +
                "password" + ":" + getPassword()+ "," +
                "isLoggedIn" + ":" + getIsLoggedIn()+ "]" + System.getProperties().getProperty("line.separator") +
                "  " + "userRole" + "=" + (getUserRole() != null ? !getUserRole().equals(this)  ? getUserRole().toString().replaceAll("  ","    ") : "this" : "null");
    }
}
