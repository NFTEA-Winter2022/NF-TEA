<template>
<div>

  <v-tabs v-model="tab" background-color="transparent" grow>
    <v-tab href="#tab-1"> Ongoing </v-tab>
    <v-tab href="#tab-2"> Accepted </v-tab>
    <v-tab href="#tab-3"> Declined </v-tab>
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
        <v-list v-bind:key="Atrade.id" v-for="Atrade in  AcceptedT">
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title> {{ Atrade.listingID }}</v-list-item-title>
              <v-list-item-subtitle>
                For {{Atrade.price}} ETH | From User {{Atrade.senderID}}
              </v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-card>
    </v-tab-item>

    <v-tab-item :key="3" value="tab-3">
      <v-card flat>
        <v-list v-bind:key="Dtrade.id" v-for="Dtrade in  DeclinedT">
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title> {{ Dtrade.listingID }}</v-list-item-title>
              <v-list-item-subtitle>
                For {{Dtrade.price}} ETH | From User {{Dtrade.senderID}}
              </v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-card>
    </v-tab-item>


  </v-tabs-items>
</div>
</template>

<script>
import apifacebook from "../api/facebook";
import blockchain from "../api/blockchain";

export default {
  name: "TradeOffersPage",
  data: () => ({
    tab: null,
    OngoingT: [],
    AcceptedT: [],
    DeclinedT: [],
    notifications: [],
  }),
  async created() {
    await this.getongoingtrade();
    await this.getacceptedtrade();
    await this.getDeclinedTrade();
  },
  methods:{
    async getongoingtrade(){
      try{
        let id = apifacebook.getCookie("id");
        this.OngoingT = (await this.$http.get('/Market/onGoingReceiver/', {
          params: {
            receiverID: id
          }
        })).data;
      }catch (e) {
        console.error(e, "Failure to Load trade offers.")
      }
    },
    async getacceptedtrade(){
      try{
        let id = apifacebook.getCookie("id");
        this.AcceptedT = (await this.$http.get('/Market/acceptedReceiver/', {
          params: {
            receiverID: id
          }
        })).data;
      }catch (e) {
        console.error(e, "Failure to Load trade offers.")
      }

    },
    async acceptTradeOffer(TradeOffer) {
      try{
        await blockchain.acceptTrade(TradeOffer.listing.nftLink, TradeOffer.senderAddress);

        await this.$http.put('/Market/acceptTradeOffer/', null, {
          params: {
            id: TradeOffer.id
          }
        })

        await this.getongoingtrade();
        await this.getacceptedtrade();
        await this.getDeclinedTrade();
      } catch (e) {
        console.error(e, "Failure to accept offer.")
      }
    },
    async declineTradeOffer(TradeOffer) {
      try{
        await this.$http.put('/Market/declineTradeOffer/', null,{
          params: {
            id: TradeOffer.id
          }
        })

        await this.getongoingtrade();
        await this.getacceptedtrade();
        await this.getDeclinedTrade();
      } catch (e) {
        console.error(e, "Failure to decline offer.")
      }
    },

    async getDeclinedTrade() {
      try {
        let id = apifacebook.getCookie("id");
        this.DeclinedT = (await this.$http.get('/Market/declinedReceiver/', {
          params: {
            receiverID: id,
          }
        })).data;
      } catch (e) {
        console.error(e, "Failure to Load Declined Trade Offers")
      }
    }
  }
}
</script>

<style scoped>

</style>