<template>
  <v-btn
      color="primary"
      text
      @click=PutIgPic()
  >
    Create
    <ul id="images">
      <div v-for="item in nft" :key="item.id" >
        <img v-if="item.media_type === 'IMAGE'" v-bind:src="item.media_url" width="800" height="682"/>
        <p v-if="item.media_type === 'IMAGE'"> {{ item.caption }} </p>
      </div>
    </ul>
  </v-btn>

</template>

<script>
import facebook from '../api/facebook.js'
import blockchain from '../api/blockchain.js'
export default ({
  data: () => ({
    nft: '',
    userid: '',
    media_type: '',
    username: '',
    media_url: '',
  }),
  methods: {
    async PutIgPic(){
      try {
        var insta = await facebook.getInstagramContent();
        this.insta = insta.data;
        console.log("Insta: " + JSON.stringify(insta.data));
        this.username = insta.data[1].username;
        await blockchain.mintNFT(insta.data[1]);
      } catch(e) {
        console.log(e);
        // this.$router.push('/api-login')
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

    else this.PutIgPic();
  }

})
</script>

<style scoped>

</style>