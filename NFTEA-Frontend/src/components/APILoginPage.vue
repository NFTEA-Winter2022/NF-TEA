<template>
  <div>
    <div>
      <h1 v-if="showConnectWallet">Connect your wallet</h1>
      <h2 v-if="!showConnectWallet">Metamask connected! To disconnect:</h2>
      <h3 style="padding-bottom: 60px;" v-if="!showConnectWallet">Go through your extension->Connected->three dots->Disconnect this account</h3>
      <vue-metamask
          userMessage="msg"
          @onComplete="onComplete"
          v-if = "showMask"
      >
      </vue-metamask>
      <v-btn v-if="showConnectWallet"
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
      <v-alert
          :value="alert1"
          shaped
          dense
          dark
          type="warning"
          transition="scale-transition"
      >
        {{msg1}}
      </v-alert>
      <h1 v-if="showConnectIG">Import your data</h1>
      <h1 style="padding-top: 60px;" v-if="!showConnectIG">Instagram Succesfully Connected!</h1>
      <v-btn v-if="showConnectIG"

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
  </div>
</template>

<script>
import FacebookAPI from "../api/facebook";
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
    msg1: "String",
    alert1: false,
    showConnectWallet : true,
    showConnectIG : true
  }),

  async created() {
    // Metamask connection dialog
    this.showConnectWallet = (await this.checkM());
    this.showConnectIG = (await this.checkIG())
    this.checkFullConnection();

    // Instagram will redirect back to this page with an auth code or with errors in the url
    const urlParams = new URLSearchParams(window.location.search);
    const code = urlParams.get('code');
    const error = {
      value: urlParams.get('error'),
      reason: urlParams.get('error_reason'),
      description: urlParams.get('error_description'),
    }

    if(code) {
      this.alert = true
      this.msg = "Authorization successful, Redirecting in 2 seconds"

      // Use the single-use auth code to get a short lived token (valid for 1 hr)
      await FacebookAPI.getToken(code.replace('#_', ''));
      console.log(this.checkIG())
      this.showConnectIG =  this.checkIG()
      this.checkFullConnection();
    } else if(error.value) {
      this.alert = true
      this.msg="You have cancelled the authorization process, please try again"
    }
  },
  methods: {
    connectInstagram() {
      FacebookAPI.authorize();
    },
    connectToMetaMask() {
      this.showMask = true
    },
    async checkM() {
      const {ethereum} = window;
      const accounts = await ethereum.request({method: 'eth_accounts'});

      if(accounts && accounts.length > 0 && !FacebookAPI.getCookie("address")) {
        document.cookie = "address=" + accounts[0] + "; path=/";
      }

      if((!accounts || accounts.length == 0) && FacebookAPI.getCookie("address")) {
        document.cookie = "address=;";
      }

      this.checkFullConnection();

      return !FacebookAPI.getCookie("address");
    },
    checkIG() {
      return !FacebookAPI.getCookie("shortIGToken");
    },
    checkFullConnection() {
      if(!this.showConnectWallet && !this.showConnectIG) {
        setTimeout(function(){
          window.location.replace(window.location.origin + '/UserProfile/');
        }, 1500);
      }
    },
    async onComplete(data) {
      if (data && data.web3) {
        console.log('data:', data);
        document.cookie = "address=" + data.metaMaskAddress + "; path=/";
        document.cookie = "metamask=" + data.netID + "; path=/";

        this.alert1 = true
        this.msg1 = "Connection successful"
        this.showConnectWallet = false;
        this.checkFullConnection();
      }
      else {
        console.log('data:', data);
        document.cookie = 'metamask=;Max-Age=0;address=;';
        this.alert1 = true
        this.msg1 = "Connection unsuccessful, please try again."
      }
      this.showMask = false
      //window.location.reload();
    }
  },
}
</script>

<style scoped>

</style>