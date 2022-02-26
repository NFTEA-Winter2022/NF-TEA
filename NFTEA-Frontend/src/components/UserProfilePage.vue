<template>
  <div>
  <div class="UserProfile">
    <h1>User Profile Page</h1>
    <h2>Username: {{ username }}</h2>
    <ul id="images">
    <div v-for="item in insta" :key="item.id" >
      <img v-if="item.media_type === 'IMAGE'" v-bind:src="item.media_url" width="800" height="682"/>
      <p v-if="item.media_type === 'IMAGE'"> {{ item.caption }} </p>
      <v-btn id="createNFTBtn" @click="NFT(item)">
        NFTEAFY!
      </v-btn>
    </div>
    </ul>
  </div>

  <v-tabs v-model="tab" background-color="transparent" grow>
      <v-tab href="#tab-1"> Instagram Content </v-tab>
      <v-tab href="#tab-2"> NFT Collections </v-tab>
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
                <v-list-item-title
                  v-text="collection.title"
                ></v-list-item-title>
                <v-list-item-subtitle
                  v-text="collection.collectionID"
                ></v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
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
  }),

  methods: {
    async verifyIGContent() {
      try {
        var insta = await facebook.getInstagramContent();
        this.insta = insta.data;
        console.log("Insta: " + JSON.stringify(insta.data));
        this.username = insta.data[1].username;
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
        // let media = {
        //   id: "17845582262609336",
        //   publisherId: "1234567",
        //   media_type: "IMAGE",
        //   media_url: "https://scontent.cdninstagram.com/v/t51.29350-15/206753475_4211925482186688_8667004301827108956_n.jpg?_nc_cat=107&ccb=1-5&_nc_sid=8ae9d6&_nc_ohc=xamL0zvoP3MAX-ocUXw&_nc_ht=scontent.cdninstagram.com&edm=ANo9K5cEAAAA&oh=00_AT8o-dgBxirB2GkzrEP1geiKf6Uea1Q2R5v08rLYdAmrBQ&oe=621F4BB9",
        //   permalink: "https://www.instagram.com/p/CQjIASNgkVCPW1uejAnRiazoT40P43UsQYzUzQ0/",
        //   thumbnail_url: "none",
        //   timestamp: "2021-06-25T15:51:08+0000",
        //   username: "Danurbae",
        //   caption: "Summer vibes~",
        // }
        blockchain.mintNFT(item);
      } catch(e) {
        console.log(e)
      }
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

    else this.verifyIGContent();
  }

})

</script>


<style>

#images {

}

</style>
