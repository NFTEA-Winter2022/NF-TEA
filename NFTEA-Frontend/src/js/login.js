import axios from 'axios'
import {route} from "express/lib/router";
var config = require('../../config')

var backendConfigurer = function () {
    switch (process.env.NODE_ENV) {
        case 'development':
            return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
        case 'production':
            return 'https://' + config.build.backendHost + ':' + config.build.backendPort
    }
}

var frontendConfigurer = function () {
    switch (process.env.NODE_ENV) {
        case 'development':
            return 'http://' + config.dev.host + ':' + config.dev.port
        case 'production':
            return 'https://' + config.build.host + ':' + config.build.port
    }
}

var backendUrl = backendConfigurer()
var frontendUrl = frontendConfigurer()

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    data () {
        return {
            userAccounts: [],
            selectedUser: '',
            userEmail: '',
            userPassword: '',

            errorClientUser: '',
            response: [],
        }
    },
    methods: {
        logInClientUser: function (userEmail, userPassword) {
            let clientUserNumberID
            AXIOS.get('/home/login', {
                params:{
                    email: userEmail,
                    password: userPassword,
                }
            })
                .then(response => {
                    this.clientUsers = ''
                    clientUserNumberID = response.data.numberID
                })
                .catch(e => {
                    var errorMsg = e.response.data.message
                    console.log(errorMsg)
                    this.errorPerson = errorMsg
                    alert(e.message)
                })

            window.location.replace("http://127.0.0.1:8087/?#/useraccount")
        }
    }
}
