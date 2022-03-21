<template>
  <div>
    <!-- TODO: US21-T03 Add the search bar here-->
    <!-- Doc: https://vuetifyjs.com/en/components/autocompletes/#dense -->
      <input
        type="text"
        name="text"
        placeholder="Search NFT"
        autocomplete="on"
        class="pa-2"
        v-model="search"
      />
  </div>
</template>

<script>
export default {
  name: "Search",
  props: ["listings"],
  data: () => ({
    search: '',
  }),
  methods: {
    filteredData: function() {
      return this.listings.filter((listing)=>{
        return listing.title.match(this.search);
      })
    }
  }
};
</script>

<style scoped>
input[type="text"] {
  width: 130px;
  -webkit-transition: width 0.4s ease-in-out;
  transition: width 0.4s ease-in-out;
}

/* When the input field gets focus, change its width to 100% */
input[type="text"]:focus {
  width: 100%;
}
</style>