<template>
  <v-app id="app" :dark="setTheme">
    <div id="nav" v-if="this.$router.currentRoute.name !== 'login' && this.$router.currentRoute.name !== 'CreateAccountFrons'">
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
      <v-btn @click="goCollection()" v-if="this.logged"
        >NFT Collection Page</v-btn
      >
      <v-btn @click="goReceipts()" v-if="this.logged">Receipts</v-btn>
      <v-btn @click="goMyListings()" v-if="this.logged">My Listings</v-btn>
      <v-btn @click="goTradeOffers()" v-if="this.logged">Trade Offers</v-btn>
      <v-btn @click="goFavourites()" v-if="this.logged">Favourite Listings</v-btn>
      <v-btn @click="goAdminPage()" v-if="this.admin && this.logged">Admin Page</v-btn>
      <v-btn @click="darkMode()" v-if="this.logged">Dark mode</v-btn>
      <v-btn @click="logout()" v-if="this.logged">Logout</v-btn>
      <v-btn @click="goProfilePage()" v-if="this.logged">Profile</v-btn>
    </div>
    <router-view />
  </v-app>
</template>

<script>
import API from "@/api/facebook";
// import vuetify from "./plugins/vuetify";

window.ethereum.on("accountsChanged", async () => {
  const { ethereum } = window;
  const accounts = await ethereum.request({ method: "eth_accounts" });
  if (accounts && accounts.length > 0) {
    console.log("user is connected");
    document.cookie = "address=" + accounts[0] + "; path=/";
  } else {
    document.cookie = "metamask=;Max-Age=0;address=;";
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
      logged: false,
      dark: false,
      admin: false,
      response:'',
      show: false,
    };
  },


  methods: {

    goProfilePage() {
      window.location.replace("/profile");
    },

    goHome() {
      window.location.replace("/home");
    },
    goAbout() {
      window.location.replace("/about");
    },
    goProfile() {
      window.location.replace("/userProfile");
    },
    goUsername() {
      window.location.replace("/user-account/editUsername");
    },
    goPassword() {
      window.location.replace("/user-account/editPassword");
    },
    goDelete() {
      window.location.replace("/delete");
    },
    goNFT() {
      window.location.replace("/NFTPage");
    },
    goCollection() {
      window.location.replace("/NFTCollection");
    },
    goMarket() {
      window.location.replace("/Market");
    },
    goSearchUser() {
      window.location.replace("/SearchUser");
    },
    goNotifications() {
      window.location.replace("/Notifications");
    },
    goMyListings() {
      window.location.replace("/myListings/" + API.getCookie("id"));
    },
    goTradeOffers() {
      window.location.replace('/myTradeOffers');
    },
    goFavourites() {
      window.location.replace('/myFavourites');
    },

    goReceipts() {
      window.location.replace("/myReceipts");
    },

    goAdminPage() {
      window.location.replace('/adminPageListing');
    },
    darkMode() {
      // this.$vuetify.theme.dark = this.dark;
      // this.dark = !this.dark;
      this.$vuetify.theme.dark = !this.$vuetify.theme.dark;
      localStorage.setItem("dark_theme", this.$vuetify.theme.dark.toString());
    },
    async logout() {
      try {
        await this.$http.put("user/logout/" + API.getCookie("id"));

        this.logged = false;

        let cookies = document.cookie.split(";");

        for (let i = 0; i < cookies.length; i++) {
          let cookie = cookies[i];
          let eqPos = cookie.indexOf("=");
          let name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
          console.log(name);
          document.cookie = name + "=;";
        }

        window.location.replace("/");
      } catch (e) {
        console.log("Logout failed." + e);
      }
    },
  },
  mounted() {
    const theme = localStorage.getItem("dark_theme");
    console.log(theme)
    if (theme) {
      if (theme === "true") {
        this.$vuetify.theme.dark = true;
      } else {
        this.$vuetify.theme.dark = false;
      }
      // } else if (
      //   window.matchMedia &&
      //   window.matchMedia("(prefers-color-scheme: dark)").matches
      // ) {
      //   this.$vuetify.theme.dark = true;
      //   localStorage.setItem("dark_theme", this.$vuetify.theme.dark.toString());
      // }
    }
  },
  beforeMount() {
    let cookies = document.cookie;
    console.log(JSON.stringify(cookies));
    let split = cookies.split(';');
    for (const element of split) {
      let name = element.split('=')[0];
      let numberID = element.split('=')[1];
      if (name === 'id') {
        console.log(numberID);
        try {
          this.$http.get('/user-account/searchAccountByUserId', {
            params: {
              id: numberID,
            }
          }).then(response => {
            this.response = response.data;

            let userAccount = response.data;
            let userAccountUR = JSON.stringify(userAccount.userRole);
            if (userAccountUR.includes("Admin")) {
              this.admin = true;
              console.log(this.admin);
            } else {
              this.admin = false;
              console.log(this.admin);
            }
          })
        } catch (e) {
          console.error(e, "Failure to get UserAccount")
        }

      }
    }
    if (cookies && API.getCookie("id")) this.logged = true;

    else if (
      window.location.pathname !== "/" &&
      window.location.pathname !== ""
    ) {
      window.location.replace("/");
    }
  },
};
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background-color: #D1BE9C;
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
