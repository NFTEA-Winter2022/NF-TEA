<template>
  <v-container grid-list-md>
    <v-row no-gutters>
      <v-col>
        <input
        type="text"
        name="text"
        placeholder="Search NFT"
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
    <v-row>
    </v-row>
    <v-layout row wrap>
      <v-flex xs12 md4 lg3 v-bind:key="listing.listingID" v-for="listing in filteredData">
        <v-hover  v-slot="{ hover }">
          <v-card :img="listing.image" height="400px" :class="{ 'on-hover': hover }">
            <div class="card-id" :class="{ 'on-hover': hover }">
              <h1 class="card-id" v-if="!hover">#</h1>
              <h1 class="card-id" v-else>#{{listing.listingID}}</h1>
            </div>
            <v-btn
                class="card-id"
                icon
                color="orange"
                @click="AddToFav(listing.listingID)"
            >
            <v-icon
                  v-if="ArrayL[listing.listingID]">mdi-star</v-icon>
              <v-icon v-else>mdi-star-off</v-icon>
            </v-btn>
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
            <v-btn class="buy-button" @click="purchaseListing(listing)">
              Buy
            </v-btn>
            
            <v-dialog
                    v-model="purchaseDialog"
                    width="300"
            >
<!--              <template v-slot:activator="{ on, attrs }">-->
<!--                <v-btn-->
<!--                        color="red lighten-2"-->
<!--                        dark-->
<!--                        v-bind="attrs"-->
<!--                        v-on="on"-->
<!--                        class="buy-button"-->
<!--                        @click="errorMessage = ''"-->
<!--                >-->
<!--                  Buy-->
<!--                </v-btn>-->
<!--              </template>-->

              <v-card>
                <v-card-title class="text-h5 grey lighten-2">
                  Purchase Confirmation
                </v-card-title>

                <v-card-text>
                  <span v-if="errorMessage" class="errorMessage">{{errorMessage}} </span> <br>
                  Listing #{{listing.listingID}}
                  <br>
                  Price: {{listing.price}}
                </v-card-text>

                <v-divider></v-divider>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn
                          color="red lighten-2"
                          text
                          @click="purchaseDialog = false"
                  >
                    Cancel
                  </v-btn>
                  <v-btn
                          color="blue lighten-2"
                          text
                          @click="purchaseListing(listing);"
                  >
                    Confirm
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>

            <v-dialog
                    v-model="transactionDialog"
                    width="500"
            >
              <v-card>
                <v-card-title class="text-h5 grey lighten-2">
                  Purchase Success!
                </v-card-title>

                <v-card-text>
                  Transaction details <br>
                  Transaction Time: {{transaction.transactionTime}}<br>
                  NFT Link: {{transaction.nftLink}} <br>
                  Price: {{transaction.price}}<br>
                  Discounted price: {{transaction.discountedPrice}}<br>
                </v-card-text>

                <v-divider></v-divider>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn
                          color="primary"
                          text
                          @click="transactionDialog = false"
                  >
                    Close
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>

            <v-dialog max-width="300px">
              <template v-slot:activator="{ on, attrs }" >
                <v-btn
                    class="trade-button"
                    v-bind="attrs"
                    v-on="on"
                >
                  Offer

                </v-btn>

              </template>

              <v-card>
                <v-card-title>
                  <span class="Title">Insert amount to trade: </span>
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
                    Send Trade
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
  name: "MarketPage",
  data: () => ({
    i:0,
    starOn:true,
    ArrayL:[],
    listings: [],
    search: '',
    filter: {
      currentFilter: "",
      availableFilters: ["Price Up", "Price Down"]
    },
    tradePrice: '',
    transaction: {},
    errorMessage: '',
    purchaseDialog: false,
    transactionDialog: false,
  }),
  async created() {
    await this.getListings();
  },
  computed: {
    filteredData: function() {
      return this.listings.filter((listing)=>{
        this.Stars(listing.listingID)
        return listing.title.toLowerCase().match(this.search.toLowerCase());
      })
    },
  },
  methods: {
    async getListings() {
      try {
        // Call API
        const listings = (await this.$http.get('UserProfilePage/getListing/')).data;
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
        console.error(e, "Failure to Load Listings.")
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
    async purchaseListing(listing) {
      // Smart Contract transaction
      console.log(listing);
      try {
        await blockchain.buyNFT(listing.nftLink, listing.price);

        await this.createTransaction(facebook.getCookie("id"), listing.listingID);
        if(!this.errorMessage) {
          this.purchaseDialog = false;
          this.transactionDialog = true;
        }

      } catch (e) {
        console.log("Could not perform smart contract transaction: \n" + e.toString());
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
    async AddToFav(listing) {
      console.log(this.ArrayL[listing])
      if (this.ArrayL[listing]) {
        try {
          await this.$http.post('/UserProfilePage/createFavourite/', null, {
            params: {
              userid: facebook.getCookie("id"),
              listingid: listing,
            },
          }).then(response => {
            this.response = response.data;
            this.ArrayL[listing]=false
          })
        } catch (e) {
          console.error(e, "Failure to send offer.");
        }
      }else{
        try {
          await this.$http.delete('/UserProfilePage/delFavouriteByUserAndListing/', {
            params: {
              userid: facebook.getCookie("id"),
              listingid: listing,
            },
          }).then(response => {
            this.response = response.data;
            this.ArrayL[listing]=true;
          })
        } catch (e) {
          console.error(e, "Failure to send offer.");
        }
      }
      await this.Stars(listing)
    },
      async Stars(listing) {
      this.ArrayL[listing]=true;
       try {
        await this.$http.get('/UserProfilePage/getFavourite/',  {
          params: {
            userid: facebook.getCookie("id"),
            listingid: listing,
          },
        }).then(response => {
          this.response = response.data;
          console.log(this.response)
          this.ArrayL[listing]=false
        })
      } catch (e){
        console.log("An error occured")
      }
    },
    async createTransaction(buyerId, listingId) {
      console.log("creating transaction");
      await this.$http.post('/market/createTransaction',null, {
        params: {
          buyerId: buyerId,
          listingId: listingId
        }
      })
              .then(response => {
                console.log(response);
                this.response = response.data;
                console.log(response.data);
                this.transaction = response.data;
                this.errorMessage = "";
              })
              .catch(e => {
                let errorMsg = e.response.data;
                console.log(errorMsg);
                this.errorMessage = errorMsg;
                this.transaction = {};
              })
    },
  },
}
</script>

<style scoped>

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
.errorMessage {
  color: red;
}

/* When the input field gets focus, change its width to 100% */
input[type="text"]:focus {
  width: 100%;
}
</style>