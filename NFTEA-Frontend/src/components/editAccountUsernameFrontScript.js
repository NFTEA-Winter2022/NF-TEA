import VueMetamask from 'vue-metamask';
import axios from 'axios'

var baseURL

var AXIOS = axios.create({
    baseURL: `http://localhost:8081/`,
})

export default {
    name: 'editAccountUsernameFrontScript',
    components: {
        VueMetamask,
    },

    data: () => ({
            error: '',
            value: String,
            show1: false,
            dialog: false
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
                AXIOS.put(baseURL + '/user-account/editUsername/' + id[1] + '?new_username=' + username ).then(response => {
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
            let url = '/user/logout/' + id[1].toString();
            console.log(url);
            AXIOS.put(url)
            .then(response => {
                this.response = response.data
                console.log(response)
                if (this.response === "Logged out") {
                    document.cookie = "id=;Max-Age=0";
                    //document.cookie = "usertype=;Max-Age=0"
                    this.$router.push('/');
                }
            }).catch(msg => {
                console.log(msg.response.data)
                console.log(msg.status)
                this.error = msg.response.data;
            })
        },
        disconnectFromIG: function () {
            document.cookie = "shortIGToken=;Max-Age=0";
        },
        beforeMount() {
            let cookies = document.cookie;
            let split = cookies.split(';');
            let log = false;
            for (const element of split) {
                let name = element.split('=')[0];
                if (name === 'id') log = true;
            }
            if (!log) window.location.replace('/');
        }

    }
}
