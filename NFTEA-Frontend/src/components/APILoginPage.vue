<template>
  <div>
    <h1>Connect your wallet</h1>
    <v-btn
        class="ma-2"
        dark
        color="orange"
        @click="connectMetamask"
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

export default {
  name: "APILoginPage",
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
    connectMetamask() {
      //TODO: US006-T03
    }
  }
}
</script>

<style scoped>

</style>