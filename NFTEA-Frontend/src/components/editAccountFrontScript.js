import VueMetamask from 'vue-metamask';
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.build.host
var backendUrl = 'http://' + config.build.backendHost

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'editAccountFrontScript',
    components: {
        VueMetamask,
    },

    data: () => ({
        error: '',
        value: String,
        show1: false
        }
    ),

    methods: {
        /* Calls updateOnlineInfo */
        updateAccountInfo: function (userName, newPassword, confirm, email) {
            if (newPassword == null || confirm == null || newPassword === '' || confirm === '' || confirm !== newPassword ) {
                this.error = "Password cannot be empty"
            }
            else {
                //Probably need to be change
                AXIOS.put(backendUrl + '/Edit/' + userName + '&password=' + newPassword + '&email=' + email).then(response => {
                    this.response = response.data
                    this.error = ''
                    console.log(response)
                    if (this.response != '') {
                        this.$router.push('/')
                    }
                }).catch(msg => {
                    console.log(msg.response.data)
                    this.error = msg.response.data;
                })
            }
        },

        cancel: function () {
            //TODO
            this.$router.push('/')
        }
    },

    beforeMount() {
        let split = document.cookie.split(';')
        let id = split[0].split('=');
        AXIOS.get(backendUrl + '/customer/' + id[1]).then(response => {
            this.response = response.data;
            this.username = this.response.username;
            this.password = this.response.password;
            this.email = this.response.email;
        }).catch(msg => {
            this.error = msg.response.data;
        })
    }
}