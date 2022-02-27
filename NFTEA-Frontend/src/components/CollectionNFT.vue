<template>
  <div class="collectionNFT">
    <template>
      <v-container fluid>
        <v-select
          v-model="selectedToAdd"
          :items="unclassifiedListings"
          item-text="listingID"
          label="Add listings to collection"
          multiple
        >
          <template v-slot:prepend-item>
            <v-list-item ripple @mousedown.prevent @click="toggle">
              <v-list-item-action>
                <v-icon
                  :color="selectedToAdd.length > 0 ? 'indigo darken-4' : ''"
                >
                  {{ icon }}
                </v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title> Select All </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-divider class="mt-2"></v-divider>
          </template>
        </v-select>
      <v-btn @click="addListings(collectionID, selectedToAdd)">
          Add Listings
        </v-btn>
      </v-container>
    </template>
    <v-data-table
      :headers="headers"
      :items="listings"
      :sort-by="title"
      class="elevation-1"
    >
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>{{ collectionTitle }}</v-toolbar-title>
          <v-divider class="mx-4" inset vertical></v-divider>
          <v-spacer></v-spacer>
        </v-toolbar>
      </template>
      <template v-slot:item.actions="{ listing }">
        <v-icon small class="mr-2" @click="removeListing(listing)">
          mdi-delete
        </v-icon>
      </template>
    </v-data-table>
  </div>
</template>
<style>
</style>
<script>
export default {
  name: "CollectionNFT",
  async created() {
    this.getCollection(this.collectionID);
  },
  data() {
    return {
      collectionID: "",
      collectionTitle: "",
      headers: [
        {
          text: "Title",
          align: "start",
          value: "title",
        },
        {
          text: "Price",
          value: "price",
        },
        {
          text: "Link",
          value: "link",
        },
        {
          text: "Listing ID",
          value: "listingID",
        },
        {
          text: "Actions",
          value: "actions",
          sortable: "false",
        },
      ],
      listings: [],
      unclassifiedListings: [],
      selectedToAdd: [],
      response: {},
      errorMsg: "",
    };
  },
  computed: {
    selectedAllListings() {
      return this.selectedToAdd.length === this.unclassifiedListings.length;
    },
    selectedSomeListings() {
      return this.selectedToAdd.length > 0 && !this.selectedAllListings;
    },
    icon() {
      if (this.selectedAllListings) {
        return "mdi-close-box";
      }
      if (this.selectedSomeListings) {
        return "mdi-minus-box";
      }
      return "mdi-checkbox-blank-outline";
    },
  },
  methods: {
    async getCollection(collectionID) {
      await this.$http
        .get("/userProfile/collections/collection", {
          params: {
            collectionID: collectionID,
          },
        })
        .then((response) => {
          this.response = response.data;
        })
        .catch((e) => {
          var errorMsg = e.response.data.message;
          console.log(errorMsg);
          this.errorMessage = errorMsg;
        });
    },
    async addListings(collectionID, listings) {
      await this.$http
        .post("/userProfile/collections/addListings", {
          params: {
            collectionID: collectionID,
            listings: listings,
          },
        })
        .then((response) => {
          this.response = response.data;
        })
        .catch((e) => {
          var errorMsg = e.response.data.message;
          console.log(errorMsg);
          this.errorMessage = errorMsg;
        });
    },
    async getUnclassifiedListings(collectionID) {
      await this.$http
        .get("/userProfile/collections/getUnclassified", {
          params: {
            collectionID: collectionID,
          },
        })
        .then((response) => {
          this.unclassifiedListings = response.data;
        })
        .catch((e) => {
          var errorMsg = e.response.data.message;
          console.log(errorMsg);
          this.errorMessage = errorMsg;
        });
    },
    async removeListing(collectionID, listing) {
      var listingID = listing.listingID;
      await this.$http
        .post("/userProfile/collections/removeListing", {
          params: {
            collectionID: collectionID,
            listingID: listingID,
          },
        })
        .then((response) => {
          this.response = response.data;
        })
        .catch((e) => {
          var errorMsg = e.response.data.message;
          console.log(errorMsg);
          this.errorMessage = errorMsg;
        });
    },
    toggle() {
      this.$nextTick(() => {
        if (this.selectedAllListings) {
          this.selectedToAdd = [];
        } else {
          this.selectedToAdd = this.unclassifiedListings
            .map(this.getListingID)
            .slice();
        }
      });
    },
    getListingID(listing) {
      return listing.listingID;
    },
  },
};
</script>