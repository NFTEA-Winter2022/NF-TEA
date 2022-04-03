import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);
var darkmode = false;
export default new Vuetify({
theme: {dark: darkmode},
});
