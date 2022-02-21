import Vue from 'vue'
import App from './App.vue'
import Vuetify from "vuetify"
import './registerServiceWorker'
import vuetify from './plugins/vuetify'
import store from './store'
import router from './router'
import Axios from 'axios'

Vue.prototype.$http = Axios.create({
  baseURL: `https://nftea-backend.herokuapp.com:8081/`
})

Vue.config.productionTip = false

Vue.use(Vuetify);

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App)
}).$mount('#app')