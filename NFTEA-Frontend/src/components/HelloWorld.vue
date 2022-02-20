<template>
  <v-container>
    <vue-metamask
        userMessage="msg"
        @onComplete="onComplete"
    >
    </vue-metamask>
    <v-row class="text-center">
      <v-col cols="12">
        <v-img
          :src="require('../assets/logo.svg')"
          class="my-3"
          contain
          height="200"
        />
      </v-col>

      <v-col class="mb-4">
        <button @click="testAxios">Click to test axios</button>
        <h1 class="display-2 font-weight-bold mb-3">
          Welcome to Vuetify
        </h1>

        <p class="subheading font-weight-regular">
          For help and collaboration with other Vuetify developers,
          <br>please join our online
          <a
            href="https://community.vuetifyjs.com"
            target="_blank"
          >Discord Community</a>
        </p>
      </v-col>

      <v-col
        class="mb-5"
        cols="12"
      >
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
        <h2 class="headline font-weight-bold mb-3">
          What's next?
        </h2>

        <v-row justify="center">
          <a
            v-for="(next, i) in whatsNext"
            :key="i"
            :href="next.href"
            class="subheading mx-3"
            target="_blank"
          >
            {{ next.text }}
          </a>
        </v-row>
      </v-col>

      <v-col
        class="mb-5"
        cols="12"
      >
        <h2 class="headline font-weight-bold mb-3">
          Important Links
        </h2>

        <v-row justify="center">
          <a
            v-for="(link, i) in importantLinks"
            :key="i"
            :href="link.href"
            class="subheading mx-3"
            target="_blank"
          >
            {{ link.text }}
          </a>
        </v-row>
      </v-col>

      <v-col
        class="mb-5"
        cols="12"
      >
        <h2 class="headline font-weight-bold mb-3">
          Ecosystem
        </h2>

        <v-row justify="center">
          <a
            v-for="(eco, i) in ecosystem"
            :key="i"
            :href="eco.href"
            class="subheading mx-3"
            target="_blank"
          >
            {{ eco.text }}
          </a>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import VueMetamask from 'vue-metamask';
  import FacebookAPI from "@/api/facebook";
  //Use the router import if $router not working

  //import router from "@/router";
  // this returns the provider, or null if it wasn't detected
  window.ethereum.on('accountsChanged', async () => {
    const {ethereum} = window;
    const accounts = await ethereum.request({method: 'eth_accounts'});
    if (accounts && accounts.length > 0) {
      console.log("user is connected");

    } else {
      document.cookie = 'metamask=;Max-Age=0';
      console.log("user not connected");


      //Please use router.replace because router.push seems to not work on an already async function
      // unless you maker work
      //router.replace({ path: '/wanted_path' });

      //Todo Upon receving the action of user disconnecting do something
    }
  });

  export default {
    name: 'HelloWorld',
    components: {
      VueMetamask,
    },

    data: () => ({
      msg1: "String",
      alert1: false,
      ecosystem: [
        {
          text: 'vuetify-loader',
          href: 'https://github.com/vuetifyjs/vuetify-loader',
        },
        {
          text: 'github',
          href: 'https://github.com/vuetifyjs/vuetify',
        },
        {
          text: 'awesome-vuetify',
          href: 'https://github.com/vuetifyjs/awesome-vuetify',
        },
      ],
      importantLinks: [
        {
          text: 'Documentation',
          href: 'https://vuetifyjs.com',
        },
        {
          text: 'Chat',
          href: 'https://community.vuetifyjs.com',
        },
        {
          text: 'Made with Vuetify',
          href: 'https://madewithvuejs.com/vuetify',
        },
        {
          text: 'Twitter',
          href: 'https://twitter.com/vuetifyjs',
        },
        {
          text: 'Articles',
          href: 'https://medium.com/vuetify',
        },
      ],
      whatsNext: [
        {
          text: 'Explore components',
          href: 'https://vuetifyjs.com/components/api-explorer',
        },
        {
          text: 'Select a layout',
          href: 'https://vuetifyjs.com/getting-started/pre-made-layouts',
        },
        {
          text: 'Frequently Asked Questions',
          href: 'https://vuetifyjs.com/getting-started/frequently-asked-questions',
        },
      ],
    }),


    methods: {

      testAxios () {
        try {
          this.$http.post('user-account/',
              null, {
                  params: {
                    firstname: "Mircea2",
                    lastname: "Gosman",
                    username: "Bob2",
                    email: "mircea@gmail.com",
                    password: "123abc782992"
                  }
              }
          ).then( (data) => {console.log(data)}, (e) => console.log(e));
        } catch(e) {
          console.log(e);
        }
      },
      onComplete(data) {

        if (data && data.web3) {
          //TODO: save data object
          this.alert1 = true
          this.msg1 = "You are successfully connected to MetaMask."
        }
        else {
          //TODO: error message
          this.alert1 = true
          this.msg1 = "You have cancelled the connection to MetaMask.Refresh the page if you want another attempt"
        }
        console.log('data:', data);
        this.showMask = false
      }

    }

  }
</script>
