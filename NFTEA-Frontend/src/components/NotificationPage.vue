<template>

  <v-container grid-list-md>
    <span v-if="noNotifications">No Unread Notifications</span>
    <v-card flat>
      <v-list v-bind:key="notification.id" v-for="notification in notifications">
        <v-list-item>
            <v-list-item-content>
              <v-list-item-title> {{ notification.title }}</v-list-item-title>
              <v-list-item-subtitle v-if="notification.tradeOffer">
                Listing {{notification.tradeOffer.listingID}} | For {{notification.tradeOffer.price}} ETH | From User {{notification.tradeOffer.senderID}}
              </v-list-item-subtitle>
              <v-list-item-subtitle v-else>
                Listing {{notification.listing.listingID}} has been sold.
              </v-list-item-subtitle>
            </v-list-item-content>
            <v-btn v-if="notification.type === 'TRADE_OFFER'" @click="acceptTradeOffer(notification)">Accept</v-btn>
            <v-btn v-if="notification.type === 'TRADE_OFFER'"  @click="declineTradeOffer(notification)">Decline</v-btn>
            <v-btn v-else @click="dismissNotification(notification)">Dismiss</v-btn>
        </v-list-item>
      </v-list>
    </v-card>
  </v-container>
</template>

<script>
import API from "../api/facebook"; // Only for the getCookie() utility method

export default {
  name: "NotificationPages",
  data: () => ({
    notifications: [],
  }),
  async created() {
    await this.getNotifications();
  },
  computed: {
    noNotifications: function() {
      if(this.notifications.length === 0) {
        return true;
      }
      return false;
    }
  },
  methods: {
    async getNotifications() {
      let response = this.notifications;
      try{
       response = (await this.$http.get('/notification/', {
          params: {
            userId: API.getCookie("id")
          }
        })).data;
        this.formatNotifications(response);
      } catch (e) {
      console.error(e, "Failure to Load Notifications.")
      }

    },
    async acceptTradeOffer(notification) {

      try{
        await this.$http.put('/Market/acceptTradeOffer/', null, {
          params: {
            id: notification.tradeOffer.id
          }
        })
        await this.getNotifications();
      } catch (e) {
        console.error(e, "Failure to accept offer.")
      }
    },
    async declineTradeOffer(notification) {
      try{
        await this.$http.put('/Market/declineTradeOffer/', {
            id: notification.tradeOffer.id
        })
        await this.getNotifications();
      } catch (e) {
        console.error(e, "Failure to accept offer.")
      }
    },
    async dismissNotification(notification) {
      try{
        await this.$http.delete('/notification/', {
           params: {
             id: notification.id
           }
         })
        await this.getNotifications();
      } catch (e) {
        console.error(e, "Failure to delete notification.")
      }
    },
    formatNotifications(raw) {
      this.notifications = [];

      raw.forEach(notification => {
        this.notifications.push(
            {
              title: notification.type === "TRADE_OFFER" ? "Trade Offer" : "Item Sold",
              ...notification
            }
        )
      })
    },
  },
  beforeMount() {
    let cookies = document.cookie;
    let split = cookies.split(';');
    let log = false;
    for (const element of split) {
      let name = element.split('=')[0];
      if (name === 'id') log = true;
    }
    if (!log) window.location.replace('/');
  },
}
</script>

<style scoped>

</style>