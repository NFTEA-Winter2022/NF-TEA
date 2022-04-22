<template>
<div class="form">
  <v-container>
    <div class="logo">
      <img alt="logo" src="../../public/img/logo/reverseLogo.png" style="width:246px;height:217px">
    </div>

    <vue-metamask
        userMessage="msg"
        @onComplete="onComplete"
    >
    </vue-metamask>
<!--    <div id="app">-->
<!--      <v-app id="inspire">-->
        <v-row justify="center">
          <v-col
              cols="5"
              sm="10"
              md="8"
              lg="6"
          >
            <v-card color="#D1BE9C" ref="form" outlined="false" >
              <v-card-text>
                <v-text-field
                    id="myfirstname"
                    ref="name"
                    v-model="name"
                    :rules="[() => !!name || 'This field is required']"
                    :error-messages="errorMessages"
                    label="FirstName"
                    placeholder="John"
                    filled
                    rounded
                    outlined
                    required
                ></v-text-field>

                <v-text-field
                    id="mylastname"
                    ref="lastname"
                    v-model="lastname"
                    :rules="[() => !!lastname || 'This field is required']"
                    :error-messages="errorMessages"
                    label="LastName"
                    placeholder="Doe"
                    filled
                    rounded
                    outlined
                    required
                ></v-text-field>

                <v-text-field
                    id="myemail"
                    ref="email"
                    v-model="email"
                    :rules="[() => !!email || 'This field is required']"
                    label="Email"
                    placeholder="JhonDoe@gmail.com"
                    filled
                    rounded
                    outlined
                    required
                ></v-text-field>

                <v-text-field
                    id="myusername"
                    ref="username"
                    v-model="username"
                    :rules="[() => !!username || 'This field is required']"
                    label="Username"
                    placeholder="Sik-K"
                    filled
                    rounded
                    outlined
                    required
                ></v-text-field>
                  <v-text-field
                      id="mypassword"
                      :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                      v-model="password"
                      :type="show1 ? 'text' : 'password'"
                      name="input-10-1"
                      label="Password"
                      counter
                      outlined
                      filled
                      rounded
                      @click:append="show1 = !show1"
                  ></v-text-field>
              </v-card-text>
<!--              <v-card-actions>-->
              <div class="buttons">
                <v-btn
                    id="cancelBtn"
                    text
                    @click="cancel()">
                  Cancel
                </v-btn>
                <v-spacer></v-spacer>
                <v-slide-x-reverse-transition>
                  <v-tooltip
                      v-if="formHasErrors"
                      left
                  >
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn
                          icon
                          class="my-0"
                          v-bind="attrs"
                          @click="resetForm"
                          v-on="on"
                      >
                        <v-icon>mdi-refresh</v-icon>
                      </v-btn>
                    </template>
                    <span>Refresh form</span>
                  </v-tooltip>
                </v-slide-x-reverse-transition>
                <v-btn
                    id="createBtn"
                    text
                    @click=Createaccount(name,lastname,username,email,password)
                >
                  Create
                </v-btn>
              </div>
<!--              </v-card-actions>-->
              <v-alert
                  :value="alert"
                  color="#2E6171"
                  text
                  outlined
                  dense
                  rounded
                  type="warning"
                  transition="scale-transition"
              >
                {{msg}}
              </v-alert>
            </v-card>
          </v-col>
        </v-row>
<!--      </v-app>-->
<!--    </div>-->
  </v-container>
</div>
</template>


<style>

  .form {
    background-color: #D1BE9C;
    height: 100%;
  }

  .buttons {
    column-count: 2;
    height: 50px;
  }

  #cancelBtn {
    border-radius: 20px;
    background-color: #7D4F50;
    height: 100%;
    color: #D1BE9C;
  }

  #createBtn {
    border-radius: 20px;
    height: 100%;
    background-color: #7D4F50;
    color: #D1BE9C;
  }

</style>


<script src="../components/createAccountFrontScript.js">
export default {
  name: "createAccountFront"
}
</script>
