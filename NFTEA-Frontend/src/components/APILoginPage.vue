<template>
  <div>
    <h1>Connect your wallet</h1>
    <vue-metamask
        userMessage="msg"
        @onComplete="onComplete"
        v-if = "showMask"
    >
    </vue-metamask>
    <v-btn
        class="ma-2"
        dark
        color="orange"
        @click="connectToMetaMask"
    >
      <v-icon
          dark
          left
      >
        mdi-ethereum
      </v-icon>Connect with Metamask
    </v-btn>
    <h1>Import your data</h1>
    <v-btn
        class="ma-2"
        dark
        color="purple"
        @click="connectInstagram"
    >
      <v-icon
          dark
          left
      >
        mdi-instagram
      </v-icon>Connect with Instagram
    </v-btn>
    <div>
      <a>Skip for now</a>
    </div>
  </div>
</template>

<script>
import FacebookAPI from "../api/facebook"
import VueMetamask from 'vue-metamask';


export default {

  name: "APILoginPage",



  components: {
    VueMetamask,
  },

  data: () => ({
    showMask : false
  }),

  created() {
    // Instagram will redirect back to this page with an auth code or with errors in the url


    const urlParams = new URLSearchParams(window.location.search);
    const code = urlParams.get('code');
    const error = {
      value: urlParams.get('error'),
      reason: urlParams.get('error_reason'),
      description: urlParams.get('error_description'),
    }

    if(code) {
      window.opener.close(); // try to close the previous window if allowed to

      // Use the single-use auth code to get a short lived token (valid for 1 hr)
      FacebookAPI.getToken(code.replace('#_', ''));
    } else if(error.value) {
      // TODO: US007-T06 create error message near / under the IG button for the user
    }
  },
  methods: {
    connectInstagram() {
      FacebookAPI.authorize();
    },
    connectToMetaMask() {
      this.showMask = true
    },
    onComplete(data) {

      if (data && data.web3) {
        //TODO: save data object
      }
      else {
        //TODO: error message
      }
      console.log('data:', data);
      this.showMask = false
    }
  }
}
</script>

<style scoped>

</style>