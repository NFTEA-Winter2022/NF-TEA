import VueMetamask from 'vue-metamask';

export default {
    name: 'createAccountFrontScript',
    components: {
        VueMetamask,
    },

    data: () => ({
        value: String,
        myfirstname:String,
        show1: false,
        alert: false,
        msg: "String"
    }),
    methods : {
         Createaccount(myfirstname,mylastname,myusername,myemail,mypassword){

            try {

                this.$http.post('user-account/',
                    null, {
                        params: {
                            firstname: myfirstname,
                            lastname: mylastname,
                            username: myusername,
                            email: myemail,
                            password: mypassword
                        }

                    }

                ).catch((error) => {
                    if (document.getElementById('myfirstname').value===''){
                        this.msg='Please enter your first name'
                    }
                    else if (document.getElementById('mylastname').value===''){
                        this.msg='Please enter your last name'
                    }
                    else if (document.getElementById('myusername').value===''){
                        this.msg='Please enter your username'
                    }
                    else if (document.getElementById('myemail').value===''){
                        this.msg='Please enter your email'
                    }
                    else if (document.getElementById('mypassword').value==='') {
                        this.msg = 'Please enter your password'
                    }else {
                        this.msg = error.response.data;
                    }

                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                    this.alert=true;

                }).then((data) => {
                    console.log(data);
                    if (data.status == 200) window.location.replace('/login');
                }, (e) => console.log(e))
            }catch(e) {
                console.log(e);
                this.alert=true;

            }
        },
        cancel() {
             window.location.replace('/');
        }

    }
}