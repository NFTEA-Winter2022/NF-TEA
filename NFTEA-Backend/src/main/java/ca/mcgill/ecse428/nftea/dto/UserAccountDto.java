package ca.mcgill.ecse428.nftea.dto;



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
    private ca.mcgill.ecse428.nftea.model.UserAccount.UserRole userRole;

    //------------------------
    // CONSTRUCTOR
    //------------------------

    public UserAccountDto(){

    }
    public UserAccountDto(Long aNumberID, String aFirstName, String aLastName, String aUserEmail, String aUsername, String aPassword, boolean aIsLoggedIn, ca.mcgill.ecse428.nftea.model.UserAccount.UserRole aUserRole)
    {
        numberID = aNumberID;
        firstName = aFirstName;
        lastName = aLastName;
        userEmail = aUserEmail;
        username = aUsername;
        password = aPassword;
        isLoggedIn = aIsLoggedIn;
        userRole = aUserRole;
    }
    public UserAccountDto(String aFirstName, String aLastName, String aUserEmail, String aUsername, String aPassword, boolean aIsLoggedIn, ca.mcgill.ecse428.nftea.model.UserAccount.UserRole aUserRole)
    {
        firstName = aFirstName;
        lastName = aLastName;
        userEmail = aUserEmail;
        username = aUsername;
        password = aPassword;
        isLoggedIn = aIsLoggedIn;
        userRole = aUserRole;
    }

    //------------------------
    // INTERFACE
    //------------------------

    public boolean setNumberID(Long aNumberID)
    {
        boolean wasSet = false;
        numberID = aNumberID;
        wasSet = true;
        return wasSet;
    }

    public boolean setFirstName(String aFirstName)
    {
        boolean wasSet = false;
        firstName = aFirstName;
        wasSet = true;
        return wasSet;
    }

    public boolean setLastName(String aLastName)
    {
        boolean wasSet = false;
        lastName = aLastName;
        wasSet = true;
        return wasSet;
    }

    public boolean setUserEmail(String aUserEmail)
    {
        boolean wasSet = false;
        userEmail = aUserEmail;
        wasSet = true;
        return wasSet;
    }

    public boolean setUsername(String aUsername)
    {
        boolean wasSet = false;
        username = aUsername;
        wasSet = true;
        return wasSet;
    }

    public boolean setPassword(String aPassword)
    {
        boolean wasSet = false;
        password = aPassword;
        wasSet = true;
        return wasSet;
    }

    public boolean setIsLoggedIn(boolean aIsLoggedIn)
    {
        boolean wasSet = false;
        isLoggedIn = aIsLoggedIn;
        wasSet = true;
        return wasSet;
    }

    public boolean setUserRole(ca.mcgill.ecse428.nftea.model.UserAccount.UserRole aUserRole)
    {
        boolean wasSet = false;
        userRole = aUserRole;
        wasSet = true;
        return wasSet;
    }

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

    public ca.mcgill.ecse428.nftea.model.UserAccount.UserRole getUserRole()
    {
        return userRole;
    }
    /* Code from template attribute_IsBoolean */
    public boolean isIsLoggedIn()
    {
        return isLoggedIn;
    }

    public void delete()
    {}


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
