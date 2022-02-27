<template>

  <div>
    <ul id="images">
      <div v-for="item in nft" :key="item[0]" >
        <img v-if="item[3] === 'IMAGE'" v-bind:src="item[4]" width="700" height="682"/>
        <p v-if="item[3] === 'IMAGE'"> {{ "NFT ID:"+item[0] }} </p>
        <v-btn v-if="item[3] === 'IMAGE'" id="category" @click="showCreateCollection()">
          <b>Add to a collection </b>
        </v-btn>
      </div>
    </ul>

    <div id="collectionCheckBox">
      <h1>
        HELLO I AM HIDDEN
      </h1>
    </div>
  </div>



</template>

<script>
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
        var image = await blockchain.getNFTs();
        // console.log(this.nft);
        console.log("Insta: " + JSON.stringify(image));
        this.nft = image;
        // console.log(this.nft);
      } catch(e) {
        console.log(e);
        // this.$router.push('/api-login')
      }
    },


    showCreateCollection() {
      var div = document.getElementById("collectionCheckBox");
      var divFlag = div.expandedFlag == true
      div.style.display = divFlag ? 'none' : 'block';
      div.expandedFlag = !divFlag;
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

    else {
      this.PutIgPic();
    }
  }

})
</script>

<style scoped>

</style>