import VueMetamask from 'vue-metamask';
import axios from 'axios'

var baseURL

var AXIOS = axios.create({
    baseURL: `http://192.168.1.4:8080/`
})

export default {
    name: 'editAccountUsernameFrontScript',
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
        updateUsername: function (username) {
            if (username == null) {
                this.error = "Password cannot be empty"
            }
            else {
                //Probably need to be change
                let splits = document.cookie.split(';');
                let id = splits[0].split('=')
                AXIOS.put(baseURL + '/user-account/editUsername/' + id + '?new_username=' + username ).then(response => {
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
        },

        signout:function () {
            let splits = document.cookie.split(';');
            let id = splits[0].split('=');
            AXIOS.put(baseURL + "/user/logout/" + id[1]).then(response => {
                this.response = response.data
                console.log(response)
                if (this.response === "Logged out") {
                    document.cookie = "NumberId=;Max-Age=0";
                    document.cookie = "usertype=;Max-Age=0"
                    this.$router.push('/');
                }
            }).catch(msg => {
                console.log(msg.response.data)
                console.log(msg.status)
                this.error = msg.response.data;
            })
        }

    }
}