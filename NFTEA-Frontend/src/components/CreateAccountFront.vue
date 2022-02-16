<template>
  <v-container>
    <vue-metamask
        userMessage="msg"
        @onComplete="onComplete"
    >
    </vue-metamask>
    <div id="app">
      <v-app id="inspire">
        <v-row justify="center">
          <v-col
              cols="5"
              sm="10"
              md="8"
              lg="6"
          >
            <v-card ref="form">
              <v-card-text>
                <v-text-field
                    id="myfirstname"
                    ref="name"
                    v-model="name"
                    :rules="[() => !!name || 'This field is required']"
                    :error-messages="errorMessages"
                    label="FirstName"
                    placeholder="John"
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
                    required
                ></v-text-field>

                <v-text-field
                    id="myemail"
                    ref="email"
                    v-model="email"
                    :rules="[() => !!email || 'This field is required']"
                    label="Email"
                    placeholder="JhonDoe@gmail.com"
                    required
                ></v-text-field>

                <v-text-field
                    id="myusername"
                    ref="username"
                    v-model="username"
                    :rules="[() => !!username || 'This field is required']"
                    label="Username"
                    placeholder="Sik-K"
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
                      @click:append="show1 = !show1"
                  ></v-text-field>

              </v-card-text>
              <v-divider class="mt-12"></v-divider>
              <v-card-actions>
                <v-btn text>
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
                    color="primary"
                    text
                    @click=Createaccount(name,lastname,username,email,password)
                >
                  Create

                </v-btn>

              </v-card-actions>
              <v-alert
                  :value="alert"
                  shaped
                  dense
                  dark
                  type="warning"
                  transition="scale-transition"
              >
                {{msg}}
              </v-alert>
            </v-card>
          </v-col>
        </v-row>
      </v-app>
    </div>
  </v-container>
</template>
<script src="../components/createAccountFrontScript.js">
export default {
  name: "createAccountFront"
}
</script>
