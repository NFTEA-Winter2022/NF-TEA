<template>
  <v-container grid-list-md>
    <v-row class="title" v-if="isCurrentUser">
      My Listings
    </v-row>
    <v-row class="title" v-else>
      Listings for User {{this.$route.params.userId}}
    </v-row>
    <v-row no-gutters>
      <v-col>
        <input
            type="text"
            name="text"
            placeholder="Search Listing"
            autocomplete="on"
            class="pa-2"
            v-model="search"
        />
      </v-col>
      <v-col
          cols="12"
          md="4"
      >
        <v-select
            class="pa-2"
            v-model="filter.currentFilter"
            :items="filter.availableFilters"
            @change="sortPrice"
            outlined
            label="Filter By"
        ></v-select>
      </v-col>
    </v-row>
    <v-layout row wrap>
      <v-flex xs12 md4 lg3 v-bind:key="listing.listingID" v-for="listing in filteredData">
        <v-hover  v-slot="{ hover }">
          <v-card :img="listing.image" height="400px" :class="{ 'on-hover': hover }">
            <div class="card-id" :class="{ 'on-hover': hover }">
              <h1 class="card-id" v-if="!hover">#</h1>
              <h1 class="card-id" v-else>#{{listing.listingID}}</h1>
            </div>
            <div class="card-id" :class="{ 'on-hover': hover }">
              <v-icon
                  class="card-id"
                  v-if="!hover"
              >
                mdi-information-variant
              </v-icon>
              <h1 class="card-id" v-else>{{listing.title}}</h1>
            </div>
            <div class="card-id" :class="{ 'on-hover': hover }">
              <v-icon
                  class="card-id"
                  v-if="!hover"
              >
                mdi-ethereum
              </v-icon>
              <h1 class="card-id" v-else>
                <v-icon>
                  mdi-ethereum
                </v-icon>
                {{listing.price}}
              </h1>
            </div>

            <v-dialog max-width="300px" v-if="isCurrentUser">
              <template v-slot:activator="{ on, attrs }" >
                <v-btn
                    class="trade-button"
                    v-bind="attrs"
                    v-on="on"
                >
                  Edit Listing
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="Title">Listing Details: </span>
                </v-card-title>
                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-text-field
                          v-model="newTitle"
                          label="New Title"
                          required
                      ></v-text-field>
                      <v-text-field
                          v-model="tradePrice"
                          label="New Trading Price"
                          required
                      ></v-text-field>
                    </v-row>
                  </v-container>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn
                      color="blue darken-1"
                      text
                      @click="removeListing(listing)"
                  >
                    Remove
                  </v-btn>
                  <v-btn
                      color="blue darken-1"
                      text
                      @click="editListing(listing)"
                  >
                    Confirm Edit
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>


            <v-dialog max-width="300px" v-if="isCurrentUser">
              <template v-slot:activator="{ on, attrs }" >
                <v-btn
                    class="trade-button"
                    v-bind="attrs"
                    v-on="on"
                >
                  Discount
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="Title">Discount in Percentage: </span>
                </v-card-title>
                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-text-field
                          v-model="discount"
                          label="Discount"
                          required
                      ></v-text-field>

                    </v-row>
                  </v-container>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn
                      color="blue darken-1"
                      text
                      @click="discountListing(listing)"
                  >
                    Confirm Discount
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>

            <v-dialog max-width="300px" v-else>
              <template v-slot:activator="{ on, attrs }" >
                <v-btn
                    class="trade-button"
                    v-bind="attrs"
                    v-on="on"
                >
                  Make Offer
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="Title">Listing Details: </span>
                </v-card-title>
                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-text-field
                          v-model="tradePrice"
                          label="Trading Price"
                          required
                      ></v-text-field>
                    </v-row>
                  </v-container>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn
                      color="blue darken-1"
                      text
                      @click="sendTrade(listing, tradePrice)"
                  >
                    <span >Send Trade</span>

                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-card>
        </v-hover>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import facebook from "@/api/facebook";
import blockchain from "@/api/blockchain";

export default {
  name: "MyListings",
  data: () => ({
    isCurrentUser: false,
    listings: [],
    search: '',
    filter: {
      currentFilter: "",
      availableFilters: ["Price Up", "Price Down"]
    },
    tradePrice: '',
    newTitle: '',
    response: '',
    discount:'',
  }),
  async created() {
    await this.getListings();
  },
  computed: {
    filteredData: function() {
      return this.listings.filter((listing)=>{
        return listing.title.toLowerCase().match(this.search.toLowerCase());
      })
    }
  },
  methods: {
    async getListings() {
      let id = this.$route.params.userId || facebook.getCookie("id");
      this.isCurrentUser = !this.$route.params.userId || this.$route.params.userId === facebook.getCookie("id");

      try {
        const listings = (await this.$http.get('UserProfilePage/getMyListings/', {
          params: {
            id: id,
          },
        })).data;

        this.listings = [];

        listings.forEach(listing => {
          this.getImage(listing.nftLink).then(nft => {
            this.listings.push(
                {
                  image:nft.URL,
                  ... listing
                }
            )
          })
        })

      } catch (e) {
        console.error(e, "Failure to Load My Listings.");
      }
    },
    async getImage(nftLink) {
      return await blockchain.getNFT(nftLink);
    },
    sortPrice() {
      if(this.filter.currentFilter === this.filter.availableFilters[0]) {
        this.listings.sort((a,b) => a.price >= b.price ? 1 : -1);
      } else if(this.filter.currentFilter === this.filter.availableFilters[1]) {
        this.listings.sort((a,b) => a.price <= b.price ? 1 : -1);
      } // Add more filters here later if wanted
    },
    async discountListing(listing){
      try {
      // Edit all of the listing properties
      if(this.discount) {
        await this.$http.put('UserProfilePage/discountListingPrice', null, {
          params: {
            listingId: listing.listingID,
            percent: this.discount
          },
        });
      }



      this.discount = '';

      // Refresh the list
      await this.getListings();
    } catch (e) {
      console.error(e, "Failure to Load My Listings.");
    }},
    async editListing(listing) {
      try {
        // Edit all of the listing properties
        if(this.newTitle) {
          await this.$http.put('UserProfilePage/editListingTitle', null, {
            params: {
              listingId: listing.listingID,
              title: this.newTitle
            },
          });
        }

        if(!isNaN(this.tradePrice) && Number(this.tradePrice) > 0) {
          await this.$http.put('UserProfilePage/editListingPrice', null, {
            params: {
              listingId: listing.listingID,
              price: this.tradePrice
            },
          });
        }

        this.tradePrice = '';
        this.newTitle = '';

        // Refresh the list
        await this.getListings();
      } catch (e) {
        console.error(e, "Failure to Load My Listings.");
      }
    },
    async sendTrade(listing, tradePrice) {
      try{
        // Backend Record
        this.response = (await this.$http.post('/Market/createTradeOffer', null, {
          params: {
            senderID: facebook.getCookie("id"),
            receiverID: listing.owner.numberID,
            listingID: listing.listingID,
            price: tradePrice,
            senderAddress: facebook.getCookie("address")
          },
        })).data;

        // Blockchain transaction
        await blockchain.offerTrade(listing.nftLink, tradePrice);
      } catch (e) {
        console.error(e, "Failure to send offer.");
      }

      this.tradePrice = '';
    },
    async removeListing(listing) {
      console.log(JSON.stringify(listing))
      try {
        await this.$http.delete('UserProfilePage/deleteListing', {
          params: {
            listingId: listing.listingID,
          }
        }).then(response => {
          this.response = response.data;
        })
      } catch (e) {
        console.error(e, "Failure to remove Listing");
      }
    },
  },
}
</script>

<style scoped>
.title {
 justify-content: center;
  margin-top: 4rem;
  margin-bottom: 4rem;
}
div.card-id {
  height: 40px;
  width: 10%;
  background-color: rgba(255,255,255, 1);
  color: #5c584e;
  font-size: 0.8rem;
  text-align: center;
  border-radius: 0 5px 5px 0px !important;
  transform: translateY(30px);
  margin-bottom: 10px;
  display: flex;
  justify-content: center;
  align-content: center;
  flex-direction: column;
}
div.card-id.on-hover {
  background-color: rgba(255,255,255, 0.8);
  width: max-content;
  min-width: 40%;
  max-width: 70%;
  white-space: nowrap;
  overflow: hidden;
}
.buy-button {
  width:50%;
  margin-top: 60%;
}

.trade-button {
  width:50%;
  margin-top: 60%;
}
input[type="text"] {
  width: 130px;
  -webkit-transition: width 0.4s ease-in-out;
  transition: width 0.4s ease-in-out;
}
</style>