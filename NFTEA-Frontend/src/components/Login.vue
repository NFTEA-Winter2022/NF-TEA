<template>
  <div class="login">
    <h1>This is the login page</h1>
    <v-form v-model="valid" id="login-form">
      <v-container>
        <span v-if="errorMessage" class="errorMessage">{{errorMessage}} </span>
        <v-row>
          <v-text-field
            v-model="userEmail"
            label="Email"
            required
          ></v-text-field>
        </v-row>
        <v-row>
          <v-text-field
            v-model="userPassword"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'" 
            :type="showPassword ? 'text' : 'password'"
            label="Password"
            required
            @click:append="showPassword = !showPassword"
          ></v-text-field>
        </v-row>
        <v-row>
          <v-col>
            <v-btn id="loginBtn"
            @click="logInClientUser(userEmail, userPassword)"
            > 
              Login
            </v-btn>
          </v-col>
          <v-col>
            <div>
              <router-link :to="{name: 'CreateAccountFrons'}">
                <p>Don't have an account?</p>
              </router-link>
            </div>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
  </div>
</template>
<style>
  #login-form {
    margin: auto;
    width: 300px;
  }
  .errorMessage {
    color: red;
  }
</style>
<script>
    import * as vm from "vm";

    export default {
    data() {
      return {
        userAccounts: [],
        selectedUser: '',
        userEmail: '',
        userPassword: '',

        showPassword: false,
        errorEmail: '',
        errorPassword: '',
        errorStatus: '',
        errorClientUser: '',
        errorMessage: '',
        response: [],
      }
    },
    methods: {
      logInClientUser(userEmail, userPassword) {
        //let currentUserEmail
        this.$http.get('/home/login', {
          params: {
            email: userEmail,
            password: userPassword,
          }
        })
            .then(response => {
              this.response = response.data;
              document.cookie = "id=" + this.response.numberID.toString() + "; path=/";
              window.location.replace("/userProfile");
              vm.$forceUpdate();
            })
            .catch(e => {
              let errorMsg = e.response.data;

              console.log(errorMsg)
              // this.errorPerson = errorMsg
              // alert(e.message)
              this.errorMessage = errorMsg

            })
      },
      beforeMount() {
        let cookies = document.cookie;
        let split = cookies.split(';');
        let log = false;
        for (const element of split) {
          let name = element.split('=')[0];
          if (name === 'id') {
            console.log(name);
            log = true;
          }
        }
        if (log) window.location.replace('/home');
      }
    }
}
</script>