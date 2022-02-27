<template>
  <div class="UserProfile">
    <h1>User Profile Page</h1>
    <h2>Username: {{ username }}</h2>

    <v-tabs v-model="tab" background-color="transparent" grow>
      <v-tab href="#tab-1"> Instagram Content </v-tab>
      <v-tab href="#tab-2"> NFT Collections </v-tab>
    </v-tabs>
    <v-tabs-items v-model="tab">
      <v-tab-item :key="1" value="tab-1">
        <v-card flat>
          <ul id="images">
            <div v-for="item in insta" :key="item.id">
              <img
                v-if="item.media_type === 'IMAGE'"
                v-bind:src="item.media_url"
                width="800"
                height="682"
              />
              <p v-if="item.media_type === 'IMAGE'">{{ item.caption }}</p>
            </div>
          </ul>
        </v-card>
      </v-tab-item>

      <v-tab-item :key="2" value="tab-2">
        <v-card flat>
          <v-list subheader two-line>
            <v-list-item
              v-for="collection in collections"
              v-bind:key="collection.collectionID"
              :to="{name: 'CollectionNFT', params: {collectionID: key}}"
            >
              <v-list-item-content>
                <v-list-item-title
                  v-text="collection.title"
                ></v-list-item-title>
                <v-list-item-subtitle
                  v-text="collection.collectionID"
                ></v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
// import { defineComponent } from '@vue/composition-api'
import facebook from "../api/facebook.js";

export default {
  async created() {
    try {
      this.fetchCollections();
      var insta = await facebook.getInstagramContent();
      this.insta = insta.data;
      console.log("Insta: " + JSON.stringify(insta.data));
      this.username = insta.data[1].username;
    } catch (e) {
      console.log(e);
      this.$router.push("/api-login");
    }
  },

  data: () => ({
    insta: "",
    userid: "",
    media_type: "",
    username: "",
    media_url: "",
    collections: [],
    tab: null,
  }),
  methods: {
    async fetchCollections() {
      let id = facebook.getCookie("id");
      await this.$http
        .get("/userProfile/collections", {
          params: {
            userID: id,
          },
        })
        .then((response) => {
          this.collections = response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>
