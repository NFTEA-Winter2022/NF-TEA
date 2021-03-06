import VueMetamask from 'vue-metamask';
// import axios from "axios";

export default {
    name: 'deleteAccountFrontScript',
    components: {
        VueMetamask,
    },

    data: () => ({
        formPassword: "",
        value: String,
        show1: false,
    }),
    methods: {
        deleteReq () {
            let splits = document.cookie.split(";");
            let userId = splits[0].split('=');
          try {
            this.$http.delete('user-account/',
                {
                    data : {
                    id: userId,
                    password: this.formPassword
                }
            }
            ).then( (data) => {console.log(data)}, (e) => console.log(e));
          } catch(e) {
            console.log(e);
          }
        },
        goHome () {
            this.$router.push('/');
        }, 
        resetForm () {
            this.formPassword = "";
        }
      },
}