/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse428.nftea.model;
import java.util.*;
import javax.persistence.*;

// line 34 "../../../../../nftea.ump"
@Entity
public class CollectionNFT
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CollectionNFT Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long collectionID;
  private String title;

  //CollectionNFT Associations
  @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
  private List<Listing> listings;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  public CollectionNFT(){
	    listings = new ArrayList<Listing>();
  }
  
  public CollectionNFT(String aTitle)
  {
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfListings()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addListing(Listing aListing)
  {
    boolean wasAdded = false;
    if (listings.contains(aListing)) { return false; }
    listings.add(aListing);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeListing(Listing aListing)
  {
    boolean wasRemoved = false;
    if (listings.contains(aListing))
    {
      listings.remove(aListing);
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
    listings.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "collectionID" + ":" + getCollectionID()+ "," +
            "title" + ":" + getTitle()+ "]";
  }
}