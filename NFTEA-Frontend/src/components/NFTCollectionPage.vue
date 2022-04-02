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
})
</script>

<style scoped>

</style>