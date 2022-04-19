<template>

  <div class="login" style="background: #D1BE9C;">

    <div class="logo">
      <img alt="logo" src="../../public/img/logo/logo.png" style="width:246px;height:217px">
    </div>

    <v-form id="login-form">
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
            <div class="noAcc">
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

  #loginBtn {
    border-radius: 20px;
    background-color: #7D4F50;
    color: #D1BE9C;
  }

  .logo {
    margin: auto;
    width: 50%;
    padding: 10px;
  }

  .login {
    height: 100%;
  }



</style>






<script>
     import * as vm from "vm";
    import API from "@/api/facebook";

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
      async logInClientUser(userEmail, userPassword) {
        //let currentUserEmail
        try {
          const id = (await this.$http.get('/home/login', {
            params: {
              email: userEmail,
              password: userPassword,
            }
          })).data.numberID
          console.log(JSON.stringify(id))
          document.cookie = "id=" + id.toString() + "; path=/";
          window.location.replace("/userProfile");
          vm.$forceUpdate();
        } catch (e) {
          console.log(e)
          this.errorMessage = e.response.data;
        }
      }
    },
      beforeMount() {
        if (document.cookie && API.getCookie("id")) window.location.replace('/home');
      }
}
</script>