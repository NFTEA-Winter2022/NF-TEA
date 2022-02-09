/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse428.nftea.model;

import javax.persistence.Entity;
import javax.persistence.Id;

// line 3 "../../../../../Hello.ump"
@Entity
public class UserAccount
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum UserRole { Admin, Customer }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UserAccount Attributes
  @Id
  private Long numberID;
  private String firstName;
  private String lastName;
  private String userEmail;
  private String username;
  private String password;
  private boolean isLoggedIn;
  private UserRole userRole;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UserAccount(){

  }
  public UserAccount(Long aNumberID, String aFirstName, String aLastName, String aUserEmail, String aUsername, String aPassword, boolean aIsLoggedIn, UserRole aUserRole)
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

  public boolean setUserRole(UserRole aUserRole)
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

  public UserRole getUserRole()
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