<!-- eslint-disable -->
<template>
  <v-container>
    <vue-metamask userMessage="msg" @onComplete="deleteReq()"> </vue-metamask>
    <div id="app">
      <v-app id="inspire">
        <v-row justify="center">
          <v-col cols="5" sm="10" md="8" lg="6">
            <v-card ref="form">
              <v-card-text>
                <v-text-field
                  :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                  :type="show1 ? 'text' : 'password'"
                  name="input-10-1"
                  label="Password"
                  v-model="formPassword"
                  counter
                  @click:append="show1 = !show1"
                ></v-text-field>
              </v-card-text>
              <v-divider class="mt-12"></v-divider>
              <v-card-actions>
                <v-btn text @click="goHome()"> Cancel </v-btn>
                <v-spacer></v-spacer>
                <v-slide-x-reverse-transition>
                  <v-tooltip>
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn
                        icon
                        class="my-0"
                        v-bind="attrs"
                        @click="resetForm()"
                        v-on="on"
                      >
                        <v-icon>mdi-refresh</v-icon>
                      </v-btn>
                    </template>
                    <span>Refresh form</span>
                  </v-tooltip>
                </v-slide-x-reverse-transition>
                <v-btn color="primary" text @click="deleteReq()"> Delete </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-app>
    </div>
  </v-container>
</template>

<script src="../components/deleteAccountFrontScript.js">
export default {
  name: "deleteAccountFront",
};
</script>