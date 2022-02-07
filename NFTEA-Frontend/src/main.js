import Vue from 'vue'
import App from './App.vue'
import Vuetify from "vuetify"
import './registerServiceWorker'
import vuetify from './plugins/vuetify'
import store from './store'

Vue.config.productionTip = false

Vue.use(Vuetify);

new Vue({
  vuetify,
  store,
  render: h => h(App)
}).$mount('#app')
