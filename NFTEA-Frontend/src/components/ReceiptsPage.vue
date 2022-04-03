<template>
  <div>

    <v-tabs v-model="tab" background-color="transparent" grow>
      <v-tab href="#tab-1"> Bought </v-tab>
      <v-tab href="#tab-2"> Sold </v-tab>

    </v-tabs>
    <v-tabs-items v-model="tab">
      <v-tab-item :key="1" value="tab-1">
        <v-card flat>
          <v-list v-bind:key="Breceipt.id" v-for="Breceipt in  BuyReceipts">
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title> {{ Breceipt.title }}</v-list-item-title>
                <v-list-item-subtitle>
                  For {{Breceipt.price}} ETH | From User {{Breceipt.seller.username}} {{Breceipt.seller.numberID}} | At {{Breceipt.transactionTime}} }
                </v-list-item-subtitle>

              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
      </v-tab-item>

      <v-tab-item :key="2" value="tab-2">
        <v-card flat>
          <v-list v-bind:key="Sreceipt.id" v-for="Sreceipt in  SoldReceipts">
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title> {{ Sreceipt.title }}</v-list-item-title>
                <v-list-item-subtitle>
                  For {{Sreceipt.price}} ETH | By User {{Sreceipt.buyer.username}} {{Sreceipt.buyer.numberID}} | At {{Sreceipt.transactionTime}}}
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
import apifacebook from "@/api/facebook";

export default {
  name: "MyReceipt",
  data: () => ({
    isCurrentUser: false,
    tab: null,
    BuyReceipts: [],
    SoldReceipts:[],
  }),
  async created() {
  await this.getBuyReceipts();
  await this.getSoldReceipts();
},
  methods:{
    async getBuyReceipts(){
      try{
        let id = apifacebook.getCookie("id");
        this.BuyReceipts = (await this.$http.get('/market/getByBuyer/', {
          params: {
            buyerId: id
          }
        })).data;
      }catch (e) {
        console.error(e, "Failure to Load Receipts.")
      }
    },
    async getSoldReceipts(){
      try{
        let id = apifacebook.getCookie("id");
        this.SoldReceipts = (await this.$http.get('/market/getBySeller/', {
          params: {
            sellerId: id
          }
        })).data;
      }catch (e) {
        console.error(e, "Failure to Load Receipts.")
      }
    }

  }

}
</script>

<style scoped>

</style>