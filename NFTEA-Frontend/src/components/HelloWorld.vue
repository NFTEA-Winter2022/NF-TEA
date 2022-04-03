<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        <v-img
          :src="require('../assets/logo.svg')"
          class="my-3"
          contain
          height="200"
        />
      </v-col>
    </v-row>
    <h1>Welcome to NF-TEA! As this project progresses, so will this homepage!</h1>
    <br>
    <br>
    <h1>Expect this page to be complete on Sprint B!</h1>
  </v-container>
</template>

<script>
  export default {
    name: 'HelloWorld',

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

    },
  }
</script>
