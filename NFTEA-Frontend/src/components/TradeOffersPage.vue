<template>
<div>

  <v-tabs v-model="tab" background-color="transparent" grow>
    <v-tab href="#tab-1"> Ongoing </v-tab>
    <v-tab href="#tab-2"> Accepted </v-tab>
  </v-tabs>
  <v-tabs-items v-model="tab">
    <v-tab-item :key="1" value="tab-1">
      <v-card flat>
        <v-list v-bind:key="OTrade.id" v-for="OTrade in  OngoingT">
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title> {{ OTrade.listingID }}</v-list-item-title>
              <v-list-item-subtitle>
               For {{OTrade.price}} ETH | From User {{OTrade.senderID}}
              </v-list-item-subtitle>

            </v-list-item-content>
            <v-btn @click="acceptTradeOffer(OTrade)">Accept</v-btn>
            <v-btn @click="declineTradeOffer(OTrade)">Decline</v-btn>
          </v-list-item>
        </v-list>
      </v-card>
    </v-tab-item>

    <v-tab-item :key="2" value="tab-2">
      <v-card flat>

      </v-card>
    </v-tab-item>


  </v-tabs-items>
</div>
</template>

<script>
import apifacebook from "../api/facebook";
export default {
  name: "TradeOffersPage",
  data: () => ({
    OngoingT: [],
    AcceptedT: [],
  }),
  async created() {

    await this.getongoingtrade();
    await this.getacceptedtrade();
  },
  methods:{

    async getongoingtrade(){
      try{
        let id = apifacebook.getCookie("id");
        this.OngoingT= (await this.$http.get('/Market/onGoingReceiver/'+id)).data;
      }catch (e) {
        console.error(e, "Failure to Load trade offers.")
      }
      
    },

    async getacceptedtrade(){
      try{
        let id = apifacebook.getCookie("id");
        this.AcceptedTT= (await this.$http.get('/Market/acceptedReceiver/'+id)).data;
      }catch (e) {
        console.error(e, "Failure to Load trade offers.")
      }

    },
    async acceptTradeOffer(TradeOffer) {

      try{
        await this.$http.put('/Market/acceptTradeOffer/', null, {
          params: {
            id: TradeOffer.id
          }
        })
        await this.getNotifications();
      } catch (e) {
        console.error(e, "Failure to accept offer.")
      }
    },
    async declineTradeOffer(TradeOffer) {
      try{
        await this.$http.put('/Market/declineTradeOffer/', {
          id: TradeOffer.id
        })
        await this.getNotifications();
      } catch (e) {
        console.error(e, "Failure to accept offer.")
      }
    },
    
    
  }
}
</script>

<style scoped>

</style>