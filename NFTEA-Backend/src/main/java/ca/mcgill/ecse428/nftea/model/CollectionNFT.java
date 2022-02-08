/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse428.nftea.model;
import java.util.*;

// line 31 "../../../../../nftea.ump"
public class CollectionNFT
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CollectionNFT Attributes
  private Long collectionID;
  private String title;

  //CollectionNFT Associations
  private UserAccount userAccounts;
  private List<Listing> listings;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CollectionNFT(Long aCollectionID, String aTitle)
  {
    collectionID = aCollectionID;
    title = aTitle;
    listings = new ArrayList<Listing>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCollectionID(Long aCollectionID)
  {
    boolean wasSet = false;
    collectionID = aCollectionID;
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

  public Long getCollectionID()
  {
    return collectionID;
  }

  public String getTitle()
  {
    return title;
  }
  /* Code from template association_GetOne */
  public UserAccount getUserAccounts()
  {
    return userAccounts;
  }

  public boolean hasUserAccounts()
  {
    boolean has = userAccounts != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Listing getListing(int index)
  {
    Listing aListing = listings.get(index);
    return aListing;
  }

  public List<Listing> getListings()
  {
    List<Listing> newListings = Collections.unmodifiableList(listings);
    return newListings;
  }

  public int numberOfListings()
  {
    int number = listings.size();
    return number;
  }

  public boolean hasListings()
  {
    boolean has = listings.size() > 0;
    return has;
  }

  public int indexOfListing(Listing aListing)
  {
    int index = listings.indexOf(aListing);
    return index;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setUserAccounts(UserAccount aUserAccounts)
  {
    boolean wasSet = false;
    UserAccount existingUserAccounts = userAccounts;
    userAccounts = aUserAccounts;
    if (existingUserAccounts != null && !existingUserAccounts.equals(aUserAccounts))
    {
      existingUserAccounts.removeCollectionNFT(this);
    }
    if (aUserAccounts != null)
    {
      aUserAccounts.addCollectionNFT(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfListings()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addListing(Listing aListing)
  {
    boolean wasAdded = false;
    if (listings.contains(aListing)) { return false; }
    listings.add(aListing);
    if (aListing.indexOfCollectionNFT(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aListing.addCollectionNFT(this);
      if (!wasAdded)
      {
        listings.remove(aListing);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeListing(Listing aListing)
  {
    boolean wasRemoved = false;
    if (!listings.contains(aListing))
    {
      return wasRemoved;
    }

    int oldIndex = listings.indexOf(aListing);
    listings.remove(oldIndex);
    if (aListing.indexOfCollectionNFT(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aListing.removeCollectionNFT(this);
      if (!wasRemoved)
      {
        listings.add(oldIndex,aListing);
      }
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
      if(index > numberOfListings()) { index = numberOfListings() - 1; }
      listings.remove(aListing);
      listings.add(index, aListing);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveListingAt(Listing aListing, int index)
  {
    boolean wasAdded = false;
    if(listings.contains(aListing))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfListings()) { index = numberOfListings() - 1; }
      listings.remove(aListing);
      listings.add(index, aListing);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addListingAt(aListing, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    if (userAccounts != null)
    {
      UserAccount placeholderUserAccounts = userAccounts;
      this.userAccounts = null;
      placeholderUserAccounts.removeCollectionNFT(this);
    }
    ArrayList<Listing> copyOfListings = new ArrayList<Listing>(listings);
    listings.clear();
    for(Listing aListing : copyOfListings)
    {
      aListing.removeCollectionNFT(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "collectionID" + ":" + getCollectionID()+ "," +
            "title" + ":" + getTitle()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "userAccounts = "+(getUserAccounts()!=null?Integer.toHexString(System.identityHashCode(getUserAccounts())):"null");
  }
}