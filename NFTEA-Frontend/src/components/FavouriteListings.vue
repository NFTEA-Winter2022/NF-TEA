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
      <v-flex xs12 md4 lg3 v-bind:key="listing.listing.listingID" v-for="listing in filteredData">
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
                @click="AddToFav(listing.listing.listingID)"
            >
              <v-icon
                  v-if="ArrayL[listing.listing.listingID]">mdi-star</v-icon>
              <v-icon v-else>mdi-star-off</v-icon>
            </v-btn>
            <div class="card-id" :class="{ 'on-hover': hover }">
              <v-icon
                  class="card-id"
                  v-if="!hover"
              >
                mdi-information-variant
              </v-icon>
              <h1 class="card-id" v-else>{{listing.listing.title}}</h1>
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
                {{listing.listing.price}}
              </h1>

            </div>
            <v-btn class="buy-button">
              Buy
            </v-btn>

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
  name: "FavouriteListings",
  data: () => ({
    i:0,
    starOn:true,
    ArrayL:[],
    listings: [],
    favlistings:[],
    search: '',
    filter: {
      currentFilter: "",
      availableFilters: ["Price Up", "Price Down"]
    },
    tradePrice: '',
  }),
  async created() {
    await this.getListings();
  },
  computed: {
    filteredData: function() {
      return this.listings.filter((listing)=>{
        this.Stars(listing.listingID)
        return listing.listing.title.toLowerCase().match(this.search.toLowerCase());
      })
    },
  },
  methods: {
    // async getListings() {
    //   await this.getFavListings();
    //   console.log(JSON.stringify(this.favlistings))
    //   for (let i = 0;i<this.favlistings.length;i++){
    //     this.listings[i]=this.favlistings[i].listing
    //   }
    //   console.log(JSON.stringify(this.listings))
    // }
    async getListings() {
      try {
        // Call API
        const listing = (await this.$http.get('/UserProfilePage/getFavourites/?userid='+facebook.getCookie("id"))).data
        this.listings = []

        listing.forEach(listing => {
          console.log(listing.listing.nftLink)
          this.getImage(listing.listing.nftLink).then(nft => {
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

    async sendTrade(listing, tradePrice) {
      console.log(JSON.stringify(tradePrice))
      try{
        await this.$http.post('/Market/createTradeOffer', null, {
          params: {
            senderID: facebook.getCookie("id"),
            receiverID: listing.owner.numberID,
            listingID: listing.listingID,
            price: tradePrice ,
          },
        }).then(response => {
          this.response = response.data;
        })
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
          await this.$http.delete('/UserProfilePage/delFavouriteByUserAndListing/',  {
            params: {
              userid: facebook.getCookie("id"),
              listingid: listing,
            },
          }).then(response => {
            this.response = response.data;
            this.ArrayL[listing]=true
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
        await this.$http.get('/UserProfilePage/getFavourite/', {
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
    }

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

/* When the input field gets focus, change its width to 100% */
input[type="text"]:focus {
  width: 100%;
}
</style>