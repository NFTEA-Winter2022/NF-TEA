<template>
  <v-container list>
    <v-layout align-center justify-center>
      <v-btn @click="changeToListings()">
        Existing Listings
      </v-btn>
    </v-layout>
    <v-row class="title">
      Existing Users
    </v-row>
    <v-layout row wrap align-center justify-center>
      <v-flex xs12 md4 lg3 v-bind:key="userAccount.numberID" v-for="userAccount in userAccounts">
        <v-list>
            <template>
              <v-list-item v-bind:key="userAccount.numberID">
                <v-content>
                  <v-list-item-title v-text="userAccount.username"></v-list-item-title>
                  <v-list-item-subtitle v-text="userAccount.numberID"></v-list-item-subtitle>
                  <v-list-item-subtitle>
                    <v-btn color="primary" text @click="removeUserAccount(userAccount.numberID)" align-center> Delete
                    </v-btn>
                  </v-list-item-subtitle>
                </v-content>
              </v-list-item>
              <v-divider
                  v-if="index < userAccounts.length - 1"
                  :key="index"
              ></v-divider>
            </template>
        </v-list>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: "AdminPageUserAccount",
  data: () => ({
    userAccounts: [
      // {
      //   numberID: 1,
      //   firstName: "Joe",
      //   lastName: "Doe",
      //   userEmail: "jdoe@mail.com",
      //   username: "jd12345",
      // },
      // {
      //   numberID: 3,
      //   firstName: "Jane",
      //   lastName: "Smith",
      //   userEmail: "jsmith@mail.com",
      //   username: "jane3421",
      // },
    ],
    response: [],
    errorMessage: '',
  }),
  async created(){
    await this.getAllUserAccounts();
  },
  methods: {
    async getAllUserAccounts() {
      await this.$http.get('/user-account/allAccounts').then(response => {
        console.log("heyyyyyyyyyyyyyy");
        this.response = response.data;
        this.userAccounts = (response.data);
        console.log(this.userAccounts);
        console.log(response.data);
        this.errorMessage = "";
      })
          .catch(e => {
            let errorMsg = e.response.data;
            console.log(errorMsg);
          })
    },
    removeUserAccount(userAccountID) {
      console.log("hit")
      try {
        this.$http.delete('/admin/deleteUserAccount', {
          params: {
            id: userAccountID,
          }
        }).then(response => {
          this.response = response.data;
          window.location.reload();
        })
      } catch (e) {
        console.error(e, "Failure to remove UserAccount")
      }
    },
    async changeToListings() {
      window.location.replace('/adminPageListing')
    },

  },


  async beforeMount() {
    let cookies = document.cookie;
    let split = cookies.split(';');
    let log = false;
    for (const element of split) {
      let name = element.split('=')[0];
      if (name === 'id') log = true;
    }
    if (!log) window.location.replace('/');
    await this.getAllUserAccounts();
  },
}
</script>

<style scoped>
.title {
  justify-content: center;
  margin-top: 4rem;
  margin-bottom: 4rem;
}

div.card-id {
  height: 40px;
  width: 10%;
  background-color: rgba(255, 255, 255, 1);
  color: #5c584e;
  font-size: 0.8rem;
  text-align: center;
  border-radius: 0 5px 5px 0px !important;
  transform: translateY(30px);
  margin-bottom: 10px;
  display: flex;
  justify-content: center;
  align-content: center;
  flex-direction: column;
}

div.card-id.on-hover {
  background-color: rgba(255, 255, 255, 0.8);
  width: max-content;
  min-width: 40%;
  max-width: 70%;
  white-space: nowrap;
  overflow: hidden;
}
</style>