/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse428.nftea.model;
import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// line 3 "../../../../../nftea.ump"
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
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  private String userEmail;
  private String username;
  private String password;
  private boolean isLoggedIn;
  private int loginAttempts;
  private LocalDateTime lastAttempt;
  private UserRole userRole;

  //UserAccount Associations
  @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
  private List<CollectionNFT> collectionNFTs;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  public UserAccount() {}

  public UserAccount(String aFirstName, String aLastName, String aUserEmail, String aUsername, String aPassword, boolean aIsLoggedIn, int aLoginAttempts, LocalDateTime aLastAttempt, UserRole aUserRole)
  {
    firstName = aFirstName;
    lastName = aLastName;
    userEmail = aUserEmail;
    username = aUsername;
    password = aPassword;
    isLoggedIn = aIsLoggedIn;
    loginAttempts = aLoginAttempts;
    lastAttempt = aLastAttempt;
    userRole = aUserRole;
    collectionNFTs = new ArrayList<CollectionNFT>();
  }

  public UserAccount(Long id, String aFirstName, String aLastName, String aUserEmail, String aUsername, String aPassword, boolean aIsLoggedIn, int aLoginAttempts, LocalDateTime aLastAttempt, UserRole aUserRole)
  {
    setNumberID(id);
    firstName = aFirstName;
    lastName = aLastName;
    userEmail = aUserEmail;
    username = aUsername;
    password = aPassword;
    isLoggedIn = aIsLoggedIn;
    loginAttempts = aLoginAttempts;
    lastAttempt = aLastAttempt;
    userRole = aUserRole;
    collectionNFTs = new ArrayList<CollectionNFT>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberID(Long aNumberID)
  {
    boolean wasSet = false;
    id = aNumberID;
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

  public boolean setLoginAttempts(int aLoginAttempts)
  {
    boolean wasSet = false;
    loginAttempts = aLoginAttempts;
    wasSet = true;
    return wasSet;
  }

  public boolean setLastAttempt(LocalDateTime aLastAttempt)
  {
    boolean wasSet = false;
    lastAttempt = aLastAttempt;
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

  public Long getId()
  {
    return id;
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

  public UserRole getUserRole()
  {
    return userRole;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsLoggedIn()
  {
    return isLoggedIn;
  }
  /* Code from template association_GetMany */
  public CollectionNFT getCollectionNFT(int index)
  {
    CollectionNFT aCollectionNFT = collectionNFTs.get(index);
    return aCollectionNFT;
  }

  public List<CollectionNFT> getCollectionNFTs()
  {
    List<CollectionNFT> newCollectionNFTs = Collections.unmodifiableList(collectionNFTs);
    return newCollectionNFTs;
  }

  public int numberOfCollectionNFTs()
  {
    int number = collectionNFTs.size();
    return number;
  }

  public boolean hasCollectionNFTs()
  {
    boolean has = collectionNFTs.size() > 0;
    return has;
  }

  public int indexOfCollectionNFT(CollectionNFT aCollectionNFT)
  {
    int index = collectionNFTs.indexOf(aCollectionNFT);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCollectionNFTs()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addCollectionNFT(CollectionNFT aCollectionNFT)
  {
    boolean wasAdded = false;
    if (collectionNFTs.contains(aCollectionNFT)) { return false; }
    collectionNFTs.add(aCollectionNFT);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCollectionNFT(CollectionNFT aCollectionNFT)
  {
    boolean wasRemoved = false;
    if (collectionNFTs.contains(aCollectionNFT))
    {
      collectionNFTs.remove(aCollectionNFT);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCollectionNFTAt(CollectionNFT aCollectionNFT, int index)
  {  
    boolean wasAdded = false;
    if(addCollectionNFT(aCollectionNFT))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCollectionNFTs()) { index = numberOfCollectionNFTs() - 1; }
      collectionNFTs.remove(aCollectionNFT);
      collectionNFTs.add(index, aCollectionNFT);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCollectionNFTAt(CollectionNFT aCollectionNFT, int index)
  {
    boolean wasAdded = false;
    if(collectionNFTs.contains(aCollectionNFT))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCollectionNFTs()) { index = numberOfCollectionNFTs() - 1; }
      collectionNFTs.remove(aCollectionNFT);
      collectionNFTs.add(index, aCollectionNFT);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCollectionNFTAt(aCollectionNFT, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    collectionNFTs.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "numberID" + ":" + getId()+ "," +
            "firstName" + ":" + getFirstName()+ "," +
            "lastName" + ":" + getLastName()+ "," +
            "userEmail" + ":" + getUserEmail()+ "," +
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "," +
            "isLoggedIn" + ":" + getIsLoggedIn()+ "," +
            "loginAttempts" + ":" + getLoginAttempts()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "lastAttempt" + "=" + (getLastAttempt() != null ? !getLastAttempt().equals(this)  ? getLastAttempt().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "userRole" + "=" + (getUserRole() != null ? !getUserRole().equals(this)  ? getUserRole().toString().replaceAll("  ","    ") : "this" : "null");
  }
}