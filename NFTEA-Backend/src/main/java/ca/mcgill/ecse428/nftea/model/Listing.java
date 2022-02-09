/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse428.nftea.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.*;

// line 22 "../../../../../Hello.ump"
@Entity
public class Listing
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Listing Attributes
  @Id
  private Long listingID;
  private String title;
  private Long price;
  private String nftLink;

  //Listing Associations
  @ManyToMany(fetch = FetchType.EAGER)
  private List<CollectionNFT> collectionNFTs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Listing(){

  }
  public Listing(Long aListingID, String aTitle, Long aPrice, String aNftLink)
  {
    listingID = aListingID;
    title = aTitle;
    price = aPrice;
    nftLink = aNftLink;
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
            "listingID" + ":" + getListingID()+ "," +
            "title" + ":" + getTitle()+ "," +
            "price" + ":" + getPrice()+ "," +
            "nftLink" + ":" + getNftLink()+ "]";
  }
}