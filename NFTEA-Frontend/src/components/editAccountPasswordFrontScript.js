import VueMetamask from 'vue-metamask';
import axios from 'axios'

var baseURL

var AXIOS = axios.create({
    baseURL: `http://192.168.1.4:8080/`
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
        updateAccountInfo: function (newPassword, confirm) {
            if (newPassword == null || confirm == null || newPassword === '' || confirm === '' || confirm !== newPassword ) {
                this.error = "Password cannot be empty"
            }
            else {
                //Probably need to be change
                let splits = document.cookie.split(';');
                let id = splits[0].split('=')
                AXIOS.put(baseURL + '/user-account/editPassword/' + id + '?old_password=' + confirm + '?new_password' + newPassword).then(response => {
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
    }
}