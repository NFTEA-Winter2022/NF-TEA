<template>
  <v-app id="app">
    <div id="nav">
      <v-btn @click="goHome()" v-if="this.logged">Home</v-btn>
      <v-btn @click="goAbout()" v-if="this.logged">About</v-btn>
      <v-btn @click="goMarket()" v-if="this.logged">Market</v-btn>
      <v-btn @click="goSearchUser()" v-if="this.logged">Search User</v-btn>
      <v-btn @click="goNotifications()" v-if="this.logged">Notifications</v-btn>
      <v-btn @click="goProfile()" v-if="this.logged">User Profile</v-btn>
      <v-btn @click="goUsername()" v-if="this.logged">Edit Username</v-btn>
      <v-btn @click="goPassword()" v-if="this.logged">Edit Password</v-btn>
      <v-btn @click="goDelete()" v-if="this.logged">Delete Account</v-btn>
      <v-btn @click="goNFT()" v-if="this.logged">NFT Page</v-btn>
      <v-btn @click="goCollection()" v-if="this.logged">NFT Collection Page</v-btn>
      <v-btn @click="goMyListings()" v-if="this.logged">My Listings</v-btn>
      <v-btn @click="goTradeOffers()" v-if="this.logged">Trade Offers</v-btn>
      <v-btn @click="goFavourites()" v-if="this.logged">Favourite Listings</v-btn>
      <v-btn v-if="this.logged">Logout</v-btn>
    </div>
    <router-view />
  </v-app>
</template>

<script>
import API from "@/api/facebook";

window.ethereum.on('accountsChanged', async () => {
  const {ethereum} = window;
  const accounts = await ethereum.request({method: 'eth_accounts'});
  if (accounts && accounts.length > 0) {
    console.log("user is connected");
    document.cookie = "address=" + accounts[0] + "; path=/";
  } else {
    document.cookie = 'metamask=;Max-Age=0;address=';
    console.log("user not connected");
    window.location.reload();
    //Please use router.replace because router.push seems to not work on an already async function
    // unless you maker work
    //router.replace({ path: '/wanted_path' });
    //Todo Upon receving the action of user disconnecting do something
  }
});

export default {
  data() {
    return {
      logged: false
    }
  },
  methods: {
    goHome() {
      window.location.replace('/home');
    },
    goAbout() {
      window.location.replace('/about');
    },
    goProfile() {
      window.location.replace('/userProfile');
    },
    goUsername() {
      window.location.replace('/user-account/editUsername');
    },
    goPassword() {
      window.location.replace('/user-account/editPassword');
    },
    goDelete() {
      window.location.replace('/delete');
    },
    goNFT() {
      window.location.replace('/NFTPage');
    },
    goCollection() {
      window.location.replace('/NFTCollection');
    },
    goMarket() {
      window.location.replace('/Market');
    },
    goSearchUser() {
      window.location.replace('/SearchUser');

    },
    goNotifications()
      {
        window.location.replace('/Notifications');
      },
    goMyListings() {
      window.location.replace('/myListings/' + API.getCookie("id"));
    },
    goTradeOffers() {
      window.location.replace('/myTradeOffers');
    },
    goFavourites() {
      window.location.replace('/myFavourites');
    }
  },
  beforeMount() {
    let cookies = document.cookie;
    let split = cookies.split(';');
    for (const element of split) {
      let name = element.split('=')[0];
      if (name === 'id') this.logged = true;
    }
  }
}
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
