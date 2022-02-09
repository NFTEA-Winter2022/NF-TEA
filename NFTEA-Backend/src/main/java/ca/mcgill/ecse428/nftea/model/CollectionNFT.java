/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse428.nftea.model;

import javax.persistence.*;

// line 30 "../../../../../Hello.ump"
@Entity
public class CollectionNFT
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CollectionNFT Attributes
  @Id
  private Long collectionID;
  private String title;

  //CollectionNFT Associations
  @ManyToOne(fetch = FetchType.EAGER)
  private UserAccount userAccount;

  //------------------------
  // CONSTRUCTOR
  //------------------------


  public CollectionNFT(){

  }
  public CollectionNFT(Long aCollectionID, String aTitle)
  {
    collectionID = aCollectionID;
    title = aTitle;
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
  public UserAccount getUserAccount()
  {
    return userAccount;
  }

  public boolean hasUserAccount()
  {
    boolean has = userAccount != null;
    return has;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setUserAccount(UserAccount aNewUserAccount)
  {
    boolean wasSet = false;
    userAccount = aNewUserAccount;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    userAccount = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "collectionID" + ":" + getCollectionID()+ "," +
            "title" + ":" + getTitle()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "userAccount = "+(getUserAccount()!=null?Integer.toHexString(System.identityHashCode(getUserAccount())):"null");
  }
}