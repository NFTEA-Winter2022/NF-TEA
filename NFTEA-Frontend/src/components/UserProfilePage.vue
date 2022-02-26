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

    NFT(media) {
      try {
        blockchain.mintNFT(media);
      } catch(e) {
        console.log(e);
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
