<template>
  <div>
    <h1 v-if="checkM()">Connect your wallet</h1>
    <h2 v-if="!checkM()">Metamask connected! To disconnect:</h2>
    <h3 style="padding-bottom: 60px;" v-if="!checkM()">Go through your extension->Connected->three dots->Disconnect this account</h3>
    <vue-metamask
        userMessage="msg"
        @onComplete="onComplete"
        v-if = "showMask"
    >
    </vue-metamask>
    <v-btn v-if="checkM()"
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
    <h1 v-if="checkIG()">Import your data</h1>
    <h1 style="padding-top: 60px;" v-if="!checkIG()">Instagram Succesfully Connected!</h1>
    <v-btn v-if="checkIG()"
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

    <v-alert
        :value="alert"
        shaped
        dense
        dark
        type="warning"
        transition="scale-transition"
    >
      {{msg}}
    </v-alert>
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
    showMask : false,
    msg: "String",
     alert: false,
  }),

  //data: () => ({
        //name: "APILoginPage",

        //msg: "String",
      //  alert: false,
    //  }
  //),
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
      this.alert = true
      this.msg = "Authorization successful"


      // Use the single-use auth code to get a short lived token (valid for 1 hr)
      FacebookAPI.getToken(code.replace('#_', ''));
    } else if(error.value) {
      // TODO: US007-T06 create error message near / under the IG button for the user
      this.alert = true
      this.msg="You have cancelled the authorization process"
    }
  },
  methods: {
    connectInstagram() {
      FacebookAPI.authorize();
    },
    connectToMetaMask() {
      this.showMask = true
    },
    checkM() {
      let splits = document.cookie.split(';');
      let bool = true;
      function checkMeta(element, bool) {
        let name = element.split('=')[0];
        if (name === 'metamask' || name === ' metamask' || bool) return false;
        return true;
      }
      splits.forEach(element => bool = checkMeta(element, bool));
      return bool;
    },
    checkIG() {
      let splitsIG = document.cookie.split(';');
      let boolIG = true;
      function checkInsta(element, boolIG) {
        let nameIG = element.split('=')[0];
        if (nameIG === 'shortIGToken' || nameIG === ' shortIGToken' || !boolIG){
          return false;
        } else {
          return true;
        }
      }
      splitsIG.forEach(element => boolIG = checkInsta(element, boolIG));
      return boolIG;
    },
    onComplete(data) {
      if (data && data.web3) {
        console.log('data:', data);
        document.cookie = "metamask=" + data.web3 + "; path=/";
      }
      else {
        //TODO: error message
        console.log('data:', data);
        document.cookie = "metamask=;Max-Age=0";
      }
      this.showMask = false
      window.location.reload();
    }
  }
}
</script>

<style scoped>

</style>