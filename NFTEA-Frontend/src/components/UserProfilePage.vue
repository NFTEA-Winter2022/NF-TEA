<template>
  <div class="UserProfile">
    <h1>User Profile Page</h1>
    <h2>Username: {{ username }}</h2>
    <ul id="images">
    <p v-for="item in insta" :key="item.id" >
      <img v-if="item.media_type === 'IMAGE'" v-bind:src="item.media_url"/>
    </p>
    </ul>
    
  </div>
 
</template>

<script>
// import { defineComponent } from '@vue/composition-api'
import facebook from '../api/facebook.js'

export default ({

    async created() {
        var insta = await facebook.getInstagramContent();
        this.insta = insta.data;
        console.log("Insta: " + JSON.stringify(insta.data));
        this.username = insta.data[1].username;
    },

   data: () => ({
    insta: '',
    userid: '',
    media_type: '',
    username: '',
    media_url: '',
  }),

})
</script>
