import Vue from 'vue'
import App from './App.vue'
import Vuetify from "vuetify"
import './registerServiceWorker'
import vuetify from './plugins/vuetify'
import store from './store'
import router from './router'

Vue.config.productionTip = false

Vue.use(Vuetify);

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App)
}).$mount('#app')
