<template>
  <div class="UserProfile">
    <h1>User Profile Page</h1>
    <v-form v-model = "valid" id="userprofile-form">
      <v-container>
        <vue-metamask
            userMessage="msg"
            @onComplete="onComplete"
        >
        </vue-metamask>
        <v-row>
          <v-text-field
            v-model="username"
            label="Username"
            required>
          </v-text-field>
        </v-row>
      </v-container>
    </v-form>
  </div>
</template>

<script>
import { defineComponent } from '@vue/composition-api'
// import facebook from '../api/facebook.js'

export default defineComponent({

    setup() {
        // let insta = facebook.getInstagramContent();
        // let userid = insta.id;
        // let media_type = insta.media_type;
        // let username = insta.username;
        // let media_url = insta.media_url;
    },

   data: () => ({
    // insta: this.insta,
    //  userid: this.userid,
    // media_type:this.media_type,
    // username: '',
    // media_url: '',
  }),
})
</script>
