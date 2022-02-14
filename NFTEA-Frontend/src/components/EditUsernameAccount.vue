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
              <h1>Edit Username</h1>
              <v-card-text>
                <v-text-field
                    ref="username"
                    v-model="username"
                    label="Username"
                    placeholder="johnD22"
                    required
                ></v-text-field>

              </v-card-text>
              <v-divider class="mt-12"></v-divider>
              <v-card-actions>
                <v-btn text @click="cancel">
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
                >
                  Edit
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-app>
    </div>
  </v-container>
</template>

<script src="../components/editAccountUsernameFrontScript.js">
export default {
  name: "editAccountUsername"
}
</script>