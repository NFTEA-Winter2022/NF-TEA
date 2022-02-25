/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse428.nftea.model;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;

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
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private UserAccount owner;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  public Listing(){}
  
  public Listing(String aTitle, Long aPrice, String aNftLink, UserAccount userAccount)
  {
    title = aTitle;
    price = aPrice;
    nftLink = aNftLink;
    this.owner = userAccount;
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

  public boolean setOwner(UserAccount userAccount)
  {
    boolean wasSet = false;
    this.owner = userAccount;
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

  public UserAccount getOwner() {
    return owner;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "listingID" + ":" + getListingID()+ "," +
            "title" + ":" + getTitle()+ "," +
            "owner" + ":" + getOwner().getUsername() + "," +
            "price" + ":" + getPrice()+ "," +
            "nftLink" + ":" + getNftLink()+ "]";
  }
}