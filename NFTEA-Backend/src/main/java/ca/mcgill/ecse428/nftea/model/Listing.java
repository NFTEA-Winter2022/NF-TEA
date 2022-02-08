/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse428.nftea.model;
import java.util.*;

// line 23 "../../../../../nftea.ump"
public class Listing
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Listing Attributes
  private Long listingID;
  private String title;
  private Long price;
  private String nftLink;

  //Listing Associations
  private UserAccount userAccount;
  private List<CollectionNFT> collectionNFTs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Listing(Long aListingID, String aTitle, Long aPrice, String aNftLink, UserAccount aUserAccount)
  {
    listingID = aListingID;
    title = aTitle;
    price = aPrice;
    nftLink = aNftLink;
    boolean didAddUserAccount = setUserAccount(aUserAccount);
    if (!didAddUserAccount)
    {
      throw new RuntimeException("Unable to create listing due to userAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    collectionNFTs = new ArrayList<CollectionNFT>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setListingID(Long aListingID)
  {
    boolean wasSet = false;
    listingID = aListingID;
    wasSet = true;
    return wasSet;
  }

  public boolean setTitle(String aTitle)
  {
    boolean wasSet = false;
    title = aTitle;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(Long aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setNftLink(String aNftLink)
  {
    boolean wasSet = false;
    nftLink = aNftLink;
    wasSet = true;
    return wasSet;
  }

  public Long getListingID()
  {
    return listingID;
  }

  public String getTitle()
  {
    return title;
  }

  public Long getPrice()
  {
    return price;
  }

  public String getNftLink()
  {
    return nftLink;
  }
  /* Code from template association_GetOne */
  public UserAccount getUserAccount()
  {
    return userAccount;
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
  /* Code from template association_SetOneToMany */
  public boolean setUserAccount(UserAccount aUserAccount)
  {
    boolean wasSet = false;
    if (aUserAccount == null)
    {
      return wasSet;
    }

    UserAccount existingUserAccount = userAccount;
    userAccount = aUserAccount;
    if (existingUserAccount != null && !existingUserAccount.equals(aUserAccount))
    {
      existingUserAccount.removeListing(this);
    }
    userAccount.addListing(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCollectionNFTs()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addCollectionNFT(CollectionNFT aCollectionNFT)
  {
    boolean wasAdded = false;
    if (collectionNFTs.contains(aCollectionNFT)) { return false; }
    collectionNFTs.add(aCollectionNFT);
    if (aCollectionNFT.indexOfListing(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCollectionNFT.addListing(this);
      if (!wasAdded)
      {
        collectionNFTs.remove(aCollectionNFT);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeCollectionNFT(CollectionNFT aCollectionNFT)
  {
    boolean wasRemoved = false;
    if (!collectionNFTs.contains(aCollectionNFT))
    {
      return wasRemoved;
    }

    int oldIndex = collectionNFTs.indexOf(aCollectionNFT);
    collectionNFTs.remove(oldIndex);
    if (aCollectionNFT.indexOfListing(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCollectionNFT.removeListing(this);
      if (!wasRemoved)
      {
        collectionNFTs.add(oldIndex,aCollectionNFT);
      }
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
    UserAccount placeholderUserAccount = userAccount;
    this.userAccount = null;
    if(placeholderUserAccount != null)
    {
      placeholderUserAccount.removeListing(this);
    }
    ArrayList<CollectionNFT> copyOfCollectionNFTs = new ArrayList<CollectionNFT>(collectionNFTs);
    collectionNFTs.clear();
    for(CollectionNFT aCollectionNFT : copyOfCollectionNFTs)
    {
      aCollectionNFT.removeListing(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "listingID" + ":" + getListingID()+ "," +
            "title" + ":" + getTitle()+ "," +
            "price" + ":" + getPrice()+ "," +
            "nftLink" + ":" + getNftLink()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "userAccount = "+(getUserAccount()!=null?Integer.toHexString(System.identityHashCode(getUserAccount())):"null");
  }
}