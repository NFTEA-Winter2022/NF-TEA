<template>
    <div>
        <h1>
            User Profile
        </h1>
    </div>
</template>

<script>
import { defineComponent } from '@vue/composition-api'
import facebook from '../api/facebook.js'

export default defineComponent({

    setup() {
        insta = facebook.getInstagramContent();
        id = insta.id;
        media_type = insta.media_type;
        username = insta.username;
        media_type = insta.media_type;
    },

   data: () => ({
    insta: '',
    id: '',
    media_type:'',
    username: '',
    media_url: '',
  }),
})
</script>
