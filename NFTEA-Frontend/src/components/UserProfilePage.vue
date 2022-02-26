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
