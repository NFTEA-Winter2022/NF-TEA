<template>
  <v-container grid-list-md>
    <v-layout align-center justify-center>
      <v-btn @click="changeToUserAccounts()">
        Existing Users
      </v-btn>
    </v-layout>
    <v-row class="title">
      Existing Listings
    </v-row>
    <v-layout align-center justify-center>
      <v-flex xs12 md4 lg3 v-bind:key="listing.listingID" v-for="listing in listings">
        <v-list>
          <template>
            <v-list-item v-bind:key="listing.listingID">
              <v-content>
                <v-list-item-title v-text="listing.title"></v-list-item-title>
                <v-list-item-subtitle v-text="listing.listingID"></v-list-item-subtitle>
                <v-list-item-subtitle>
                  <v-btn color="primary" text @click="removeListing(listing.listingID)" align-center> Delete
                  </v-btn>
                </v-list-item-subtitle>
              </v-content>

            </v-list-item>
            <v-divider
                v-if="index < listings.length - 1"
                :key="index"
            ></v-divider>
          </template>
        </v-list>
      </v-flex>
    </v-layout>
  </v-container>

</template>
<script>
export default {
  name: "AdminPageListing",
  data: () => ({
    listings: [],
    response: [],
    errorMessage: '',
  }),
  async created(){
    await this.getAllListings();
  },
  methods:{
    async getAllListings(){
      // let id = this.$route.params.userId;
      await this.$http.get('UserProfilePage/getListing').then(response => {
        this.response = response.data;
        this.listings = (response.data);
        this.errorMessage = "";
      }).catch(e => {
        let errorMsg = e.response.data;
        console.log(errorMsg);
      })

    },
    removeListing(listing) {
      console.log(JSON.stringify(listing))
      try {
        this.$http.delete('UserProfilePage/deleteListing', {
          params: {
            listingId: listing,
          }
        }).then(response => {
          this.response = response.data;
          window.location.reload();
        })
      } catch (e) {
        console.error(e, "Failure to remove Listing");
      }
    },
    async changeToUserAccounts(){
      window.location.replace('/adminPageUserAccount')
    }
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