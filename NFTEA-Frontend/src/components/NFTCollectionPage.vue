<template>
  <v-btn id="createNFTBtn" @click="NFT(item)">
    Hello Dan(:
  </v-btn>
</template>

<script>
export default ({
  data: () => ({

  }),
  methods: {

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

//    else this.verifyIGContent();
  }

})
</script>

<style scoped>

</style>