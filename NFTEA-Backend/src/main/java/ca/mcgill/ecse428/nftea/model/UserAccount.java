/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse428.nftea.model;
import java.util.*;

// line 3 "../../../../../nftea.ump"
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
  private Long numberID;
  private String firstName;
  private String lastName;
  private String userEmail;
  private String username;
  private String password;
  private boolean isLoggedIn;
  private UserRole userRole;

  //UserAccount Associations
  private List<Listing> listing;
  private List<CollectionNFT> collectionNFTs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

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
    listing = new ArrayList<Listing>();
    collectionNFTs = new ArrayList<CollectionNFT>();
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
  /* Code from template association_GetMany */
  public Listing getListing(int index)
  {
    Listing aListing = listing.get(index);
    return aListing;
  }

  public List<Listing> getListing()
  {
    List<Listing> newListing = Collections.unmodifiableList(listing);
    return newListing;
  }

  public int numberOfListing()
  {
    int number = listing.size();
    return number;
  }

  public boolean hasListing()
  {
    boolean has = listing.size() > 0;
    return has;
  }

  public int indexOfListing(Listing aListing)
  {
    int index = listing.indexOf(aListing);
    return index;
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
  public static int minimumNumberOfListing()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Listing addListing(Long aListingID, String aTitle, Long aPrice, String aNftLink)
  {
    return new Listing(aListingID, aTitle, aPrice, aNftLink, this);
  }

  public boolean addListing(Listing aListing)
  {
    boolean wasAdded = false;
    if (listing.contains(aListing)) { return false; }
    UserAccount existingUserAccount = aListing.getUserAccount();
    boolean isNewUserAccount = existingUserAccount != null && !this.equals(existingUserAccount);
    if (isNewUserAccount)
    {
      aListing.setUserAccount(this);
    }
    else
    {
      listing.add(aListing);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeListing(Listing aListing)
  {
    boolean wasRemoved = false;
    //Unable to remove aListing, as it must always have a userAccount
    if (!this.equals(aListing.getUserAccount()))
    {
      listing.remove(aListing);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addListingAt(Listing aListing, int index)
  {  
    boolean wasAdded = false;
    if(addListing(aListing))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfListing()) { index = numberOfListing() - 1; }
      listing.remove(aListing);
      listing.add(index, aListing);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveListingAt(Listing aListing, int index)
  {
    boolean wasAdded = false;
    if(listing.contains(aListing))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfListing()) { index = numberOfListing() - 1; }
      listing.remove(aListing);
      listing.add(index, aListing);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addListingAt(aListing, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCollectionNFTs()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addCollectionNFT(CollectionNFT aCollectionNFT)
  {
    boolean wasAdded = false;
    if (collectionNFTs.contains(aCollectionNFT)) { return false; }
    UserAccount existingUserAccounts = aCollectionNFT.getUserAccounts();
    if (existingUserAccounts == null)
    {
      aCollectionNFT.setUserAccounts(this);
    }
    else if (!this.equals(existingUserAccounts))
    {
      existingUserAccounts.removeCollectionNFT(aCollectionNFT);
      addCollectionNFT(aCollectionNFT);
    }
    else
    {
      collectionNFTs.add(aCollectionNFT);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCollectionNFT(CollectionNFT aCollectionNFT)
  {
    boolean wasRemoved = false;
    if (collectionNFTs.contains(aCollectionNFT))
    {
      collectionNFTs.remove(aCollectionNFT);
      aCollectionNFT.setUserAccounts(null);
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
    for(int i=listing.size(); i > 0; i--)
    {
      Listing aListing = listing.get(i - 1);
      aListing.delete();
    }
    while( !collectionNFTs.isEmpty() )
    {
      collectionNFTs.get(0).setUserAccounts(null);
    }
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