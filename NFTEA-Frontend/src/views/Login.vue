<template>
  <div class="login">
    <h1>This is the login page</h1>
    <v-form v-model="valid" id="login-form">
      <v-container>
        <v-row>
          <v-text-field
            v-model="username"
            :rules="usernameRules"
            label="Username"
            required
          ></v-text-field>
        </v-row>
        <v-row>
          <v-text-field
            v-model="password"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'" 
            :rules="passwordRules"
            :type="showPassword ? 'text' : 'password'"
            label="Password"
            required
            @click:append="showPassword = !showPassword"
          ></v-text-field>
        </v-row>
        <v-row>
          <v-col>
            <v-btn
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
</style>
<script>
export default {
  data() {
    return {
      showPassword: false
    }
  }
}
</script>