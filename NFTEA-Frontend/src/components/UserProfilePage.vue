<template>
  <div class="UserProfile">
    <h1>User Profile Page</h1>
    <h2>{{ username }}</h2>
  </div>
</template>

<script>
import { defineComponent } from '@vue/composition-api'
import facebook from '../api/facebook.js'

export default defineComponent({

    created() {
        let insta = facebook.getInstagramContent();
        console.log("Insta: " + JSON.stringify(insta));
        this.media_type = insta.media_type;
        this.username = insta.username;
        this.media_url = insta.media_url;
    },

   data: () => ({
    insta: '',
    userid: '',
    media_type: '',
    username: 'hello',
    media_url: '',
  }),

})
</script>
