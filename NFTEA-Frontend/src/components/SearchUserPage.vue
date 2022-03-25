<template>
    <v-container grid-list-md>
        <v-row no-gutters>
            <v-col>
                <input
                        type="text"
                        name="text"
                        placeholder="Search User"
                        autocomplete="off"
                        class="pa-2"
                        v-model="searchString"
                        v-on:keyup.enter="searchUsers()"
                />
            </v-col>

            <v-col
                    cols="12"
                    md="4"
            >
                <v-btn @click="searchUsers()"
                >Search User</v-btn>
            </v-col>
        </v-row>
        <span v-if="errorMessage" class="errorMessage">{{errorMessage}} </span>
        <span v-if="noUser">No User Found</span>
        <v-card flat>
            <v-list subheader two-line>
                <template v-for="(userAccount, index) in userAccounts">
<!--                    TODO: change the router to display the user profile instead-->
                    <v-list-item
                            :key="userAccount.numberID"
                            @click="$router.push({name: 'Market', params: {}})"
                    >
                        <v-list-item-content>
                            <v-list-item-title v-text="userAccount.username"></v-list-item-title>
                            <v-list-item-subtitle v-text="userAccount.numberID"></v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider
                            v-if="index < userAccounts.length - 1"
                            :key="index"
                    ></v-divider>
                </template>
            </v-list>
        </v-card>
    </v-container>
</template>

<script>

    export default {
        name: "SearchUserPage",
        data: () => ({
            userAccounts: [
                {
                    numberID: 1,
                    firstName: "Joe",
                    lastName: "Doe",
                    userEmail: "jdoe@mail.com",
                    username: "jd12345",
                },
                {
                    numberID: 3,
                    firstName: "Jane",
                    lastName: "Smith",
                    userEmail: "jsmith@mail.com",
                    username: "jane3421",
                },
            ],
            searchString: '',
            errorMessage: '',
            response: [],
        }),
        computed: {
            noUser: function() {
                if(this.userAccounts.length === 0) {
                    return true;
                }
                return false;
            }
        },
        methods: {
            async findUserByUserID(userID) {
                await this.$http.get('/user-account/searchAccount/searchAccountByUserId', {
                    params: {
                        id: userID
                    }
                })
                    .then(response => {
                        this.response = response.data;
                        this.userAccounts.push(response.data);
                    })
                    .catch(e => {
                        let errorMsg = e.response.data;
                        console.log(errorMsg);
                        this.errorMessage = errorMsg;
                    })

            },
            async findUserByUsername(username) {
                this.$http.get('/user-account/searchAccount/searchAccountByUsername', {
                    params: {
                        username: username
                    }
                })
                    .then(response => {
                        this.response = response.data;
                        this.userAccounts.push(response.data);
                    })
                    .catch(e => {
                        let errorMsg = e.response.data;
                        console.log(errorMsg);
                        this.errorMessage = errorMsg;
                    })
            },
            async searchUsers() {
                console.log("Searching user");
                this.userAccounts = [];
                if(!isNaN(this.searchString) && parseInt(this.searchString) >= 0) {
                    await this.findUserByUserID(parseInt(this.searchString));
                }
                await this.findUserByUsername(this.searchString.trim());
            }
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
        },
    }
</script>

<style scoped>

    div.card-id {
        height: 40px;
        width: 10%;
        background-color: rgba(255,255,255, 1);
        color: #5c584e;
        font-size: 0.8rem;
        text-align: center;
        border-radius: 0 5px 5px 0px !important;
        transform: translateY(30px);
        margin-bottom: 10px;
        display: flex;
        justify-content: center;
        align-content: center;
        flex-direction: column;
    }
    div.card-id.on-hover {
        background-color: rgba(255,255,255, 0.8);
        width: max-content;
        min-width: 40%;
        max-width: 70%;
        white-space: nowrap;
        overflow: hidden;
    }
    .buy-button {
        width:50%;
        margin-top: 60%;
    }
    input[type="text"] {
        width: 130px;
        -webkit-transition: width 0.4s ease-in-out;
        transition: width 0.4s ease-in-out;
    }

    /* When the input field gets focus, change its width to 100% */
    input[type="text"]:focus {
        width: 100%;
    }
</style>