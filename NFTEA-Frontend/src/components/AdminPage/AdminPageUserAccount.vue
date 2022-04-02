<template>
  <v-container grid-list-md>
    <v-row class="title">
      Existing Users
    </v-row>
    <v-layout row wrap>
      <v-flex xs12 md4 lg3 v-bind:key="listing.listingID" v-for="listing in filteredData">
        <v-hover  v-slot="{ hover }">
          <v-card :img="listing.nftLink" height="400px" :class="{ 'on-hover': hover }">
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
                  <!--                  <v-btn-->
                  <!--                      color="blue darken-1"-->
                  <!--                      text-->
                  <!--                      @click="editListing(listing)"-->
                  <!--                  >-->
                  <!--                    Confirm Edit-->
                  <!--                  </v-btn>-->
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
export default {
  name: "AdminPage",
  data: () => ({
    isCurrentUser: false,
    // listings: [],
    userAccounts: [],
    search: '',
    tradePrice: '',
    newTitle: '',
    response: '',
  }),
  async created(){
    await this.getAllListings();
  },
  methods:{
    async getAllUserAccounts(){
      try{
        this.userAccounts = (await this.$http.get('/user-account/allAccounts')).data;
      } catch (e){
        console.error(e, "Failure to load all users");
      }
    },

    async removeUserAccount(userAccount){
      console.log(JSON.stringify(userAccount))
      try{
        await this.$http.delete('/user-account/delete', {
          params:{
            userAccountId: userAccount.userAccountId,
          }
        }).then(response => {
          this.response = response.data;
        })
      } catch (e){
        console.error(e, "Failure to remove UserAccount")
      }
    },
  },
  beforeMount() {
    let cookies = document.cookie;
    let split = cookies.split(';');
    let log = false;
    for (const element of split) {
      let name = element.split('=')[0];
      if (name === 'id') log = true;
    }
    if (!log) window.location.replace('/');
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
</style>