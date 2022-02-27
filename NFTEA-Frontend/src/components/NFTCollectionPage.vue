<template>
  <v-btn id="createCollection" @click="createCollection(Title)">
    <b>Add A Collection of your NFTEA's!</b>
  </v-btn>





</template>

<script>
export default ({
  data: () => ({
    collections: [],
  }),
  methods: {

    createCollection() {
        try {
          this.$http.post()
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

//    else this.verifyIGContent();
  }

})
</script>

<style scoped>

</style>