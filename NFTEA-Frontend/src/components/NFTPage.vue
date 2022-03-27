<template>

  <div>
    <ul id="images">
      <div v-for="item in nft" :key="item[0]" >
        <img v-if="item[3] === 'IMAGE'" v-bind:src="item[4]" width="700" height="682"/>
        <p v-if="item[3] === 'IMAGE'"> {{ "NFT ID:"+item[0] }} </p>

        <input id="collectionInput" type="text" v-model="collection" v-if="item[3] === 'IMAGE'" placeholder="Collection Name" required>
        <v-btn v-if="item[3] === 'IMAGE'" id="category" @click="addToCollection(item[0])">
          <b>Add to the collection </b>
        </v-btn>
      </div>
    </ul>
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
    collection: '',
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


    async addToCollection(NFTid) {
      try {
        let collectionName = document.getElementById("collectionInput").value;
        console.log(collectionName);
        await blockchain.changeCollection(NFTid, collectionName);
      }catch(e) {
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

    else {
      this.PutIgPic();
    }
  }

})
</script>

<style scoped>

</style>