<template>
  <div>
  <v-tabs v-model="tab" background-color="transparent" grow>
      <v-tab href="#tab-1"> Instagram Content </v-tab>
      <v-tab href="#tab-2"> NFT Collections </v-tab>
      <v-tab href="#tab-3" v-if = "selectedCollection"> {{selectedCollection}} </v-tab>
    </v-tabs>
    <v-tabs-items v-model="tab">
      <v-tab-item :key="1" value="tab-1">
        <v-card flat>
          <ul id="images">
            <div v-for="item in insta" :key="item.id">
              <img
                v-if="item.media_type === 'IMAGE'"
                v-bind:src="item.media_url"
                width="800"
                height="682"
              />
              <p v-if="item.media_type === 'IMAGE'">{{ item.caption }}</p>
              <v-btn v-if="item.media_type === 'IMAGE'" id="createNFTBtn" @click="NFT(item)">
                <b>NFTEAFY!</b>
              </v-btn>
            </div>
          </ul>
        </v-card>
      </v-tab-item>

      <v-tab-item :key="2" value="tab-2">
        <v-card flat>
          <v-list subheader two-line>
            <v-list-item
              v-for="collection in collections"
              :key="collection.collectionID"
            >
              <v-list-item-content>
                <v-btn @click="getNFTByCollection(collection)">
                  <v-list-item-title
                      v-text="collection"
                  ></v-list-item-title>
                </v-btn>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
      </v-tab-item>

    <v-tab-item :key="3" value="tab-3">
      <v-card flat>
        <ul id="image">
          <div v-for="item in this.listOfNFTS" :key="item[0]" >
            <img v-if="item[3] === 'IMAGE'" v-bind:src="item[4]" width="700" height="682"/>
            <p v-if="item[3] === 'IMAGE'"> {{ "NFT ID:"+item[0] }} </p>
            <v-btn @Click="removeNFTFromCollection(item[0])">Remove From Collection</v-btn>
          </div>
        </ul>
      </v-card>

      <v-sheet
          class="px-7 pt-7 pb-4 mx-auto text-center d-inline-block"
          color="blue-grey darken-3"
          dark
      >
        <div class="grey--text text--lighten-1 text-body-2 mb-4">
          Are you sure you want to delete this collection?
        </div>

        <v-btn
            :disabled="loading"
            class="ma-1"
            color="grey"
            plain
        >
          Cancel
        </v-btn>

        <v-btn
            :loading="loading"
            class="ma-1"
            color="error"
            plain
            @click="DeleteCollection()"

        >
          Delete
        </v-btn>
        <v-alert
            :value="alert1"
            shaped
            dense
            dark
            prominent
            type="success"
            transition="scale-transition"
        >
          {{msg1}}
        </v-alert>
      </v-sheet>

    </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
// import { defineComponent } from '@vue/composition-api'

import facebook from '../api/facebook.js'
import blockchain from '../api/blockchain.js'

export default ({
  data: () => ({
    insta: '',
    userid: '',
    media_type: '',
    username: '',
    media_url: '',
    collections: [],
    tab: null,
    listing: [],
    error: '',
    selectedCollection: '',
    listOfNFTS: [],
    loading : false,
    msg1: "String",
    alert1: false
  }),

  methods: {
    async verifyIGContent() {
      try {
        var insta = await facebook.getInstagramContent();
        this.insta = insta.data;
        console.log("Insta: " + JSON.stringify(insta.data));
        this.username = insta.data[0].username;
      } catch(e) {
        console.log(e);
        this.$router.push('/api-login')
      }
    },

    async fetchCollections() {
      let id = facebook.getCookie("id");
      await this.$http
        .get("/userProfile/collections", {
          params: {
            userID: id,
          },
        })
        .then((response) => {
          this.collections = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },

    NFT(item) {
      try {
        return blockchain.mintNFT(item);
      } catch(e) {
        console.log(e)
      }
    },

    async DeleteCollection() {
      this.loading = true
      await new Promise(resolve => setTimeout(resolve, 3000))
      this.alert1 = true
      this.msg1="You deleted the collection,Refresh to see the result"
      await blockchain.deleteCollection(this.selectedCollection);
      this.loading = false
    },

    async getNFTByCollection(collection)  {
      try {
        this.listOfNFTS = await blockchain.getNFTsByCollection(collection);
        this.selectedCollection = collection;
        console.log(JSON.stringify(this.listOfNFTS));
      } catch(e) {
        console.log(e);
      }
    }
  },
  async beforeMount() {
      await this.verifyIGContent();

      // Display collection names
      this.collections = [...new Set(await blockchain.getCollectionNamesForUser())];
      // this.collections = this.collections.splice(this.collections.indexOf('None') -1, 1)
      console.log(JSON.stringify(this.collections));

  }
})

</script>


<style>

#images {

}

</style>
