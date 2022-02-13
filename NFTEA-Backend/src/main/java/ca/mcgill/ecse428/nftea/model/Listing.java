/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse428.nftea.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// line 26 "../../../../../nftea.ump"
@Entity
public class Listing
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Listing Attributes
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long listingID;
  private String title;
  private Long price;
  private String nftLink;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  public Listing(){}
  
  public Listing(String aTitle, Long aPrice, String aNftLink)
  {
    title = aTitle;
    price = aPrice;
    nftLink = aNftLink;
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

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "listingID" + ":" + getListingID()+ "," +
            "title" + ":" + getTitle()+ "," +
            "price" + ":" + getPrice()+ "," +
            "nftLink" + ":" + getNftLink()+ "]";
  }
}