<template>
  <div class="login">
    <h1>This is the login page</h1>
    <v-form v-model="valid" id="login-form">
      <v-container>
        <v-row>
          <v-text-field
            v-model="email"
            label="Email"
            required
          ></v-text-field>
        </v-row>
        <span v-if="errorEmail" class="errorMessage">{{errorEmail}} </span>
        <v-row>
          <v-text-field
            v-model="password"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'" 
            :type="showPassword ? 'text' : 'password'"
            label="Password"
            required
            @click:append="showPassword = !showPassword"
          ></v-text-field>
        </v-row>
        <span v-if="errorPassword" class="errorMessage">{{errorPassword}} </span>
        <span v-if="errorStatus" class="errorMessage">{{errorStatus}} </span>
        <v-row>
          <v-col>
            <v-btn
            @click="login(email, password)"
            > 
              Login
            </v-btn>
          </v-col>
          <v-col>
            <div>
              <router-link :to="{name: 'About'}">
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
export default {
  data() {
    return {
      showPassword: false,
      errorEmail: '',
      errorPassword: '',
      errorStatus: ''
    }
  },
  methods: {
    login: function(email, password) {
       if(email != "hello") {
         this.errorEmail = 'Email is not associated with an account';
         this.errorPassword = ''
       } else if(password != "world") {
         this.errorPassword = "Incorrect password";
         this.errorEmail = '';
       } else {
         this.errorEmail = '';
         this.errorPassword = '';
       }
    }
  }
}
</script>