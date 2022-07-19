import Vue from 'vue'
import VueRouter from 'vue-router'
import HelloWorld from '../components/HelloWorld.vue'
import CreateAccountFront from '../components/CreateAccountFront';
import DeleteAccountFront from '../components/DeleteAccountFront';
import editPasswordAccount from "@/components/EditPasswordAccount";
import editUsernameAccount from "@/components/EditUsernameAccount";
import APILoginPage from "../components/APILoginPage";
import UserProfilePage from "@/components/UserProfilePage";
import Login from "@/components/Login";
import NFTPage from "@/components/NFTPage";
import NFTCollectionPage from "@/components/NFTCollectionPage";
import MarketPage from "@/components/Market/MarketPage";
import SearchUserPage from "@/components/SearchUserPage";
import NotificationPage from "@/components/NotificationPage";
import myListings from "@/components/myListings";
import TradeOffersPage from "@/components/TradeOffersPage";
import ReceiptsPage from "@/components/ReceiptsPage";
import FavouriteListings from "@/components/FavouriteListings";
import AdminPageListing from "@/components/AdminPage/AdminPageListing";
import AdminPageUserAccount from "@/components/AdminPage/AdminPageUserAccount";
import profilePage from "@/components/profilePage"

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/home',
    name: 'HelloWorld',
    component: HelloWorld
  },
  {
    path:'/create',
    name: 'CreateAccountFrons',
    component: CreateAccountFront
  },
  {
    path:'/user-account/editPassword',
    name: 'EditPasswordAccountFrontS',
    component: editPasswordAccount
  },
  {
    path:'/user-account/editUsername',
    name: 'EditUsernameAccountFrontS',
    component: editUsernameAccount
  },
  {
    path:'/api-login',
    name: 'APILoginPage',
    component: APILoginPage
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/delete',
    name: 'DeleteAccountFront',
    component: DeleteAccountFront
  },
  {
    path: '/userProfile',
    name: 'UserProfile',
    component: UserProfilePage

  },
  {
    path: '/NFTPage',
    name: 'NFTPage',
    component: NFTPage

  },
  {
    path: '/NFTCollection',
    name: 'NFTCollection',
    component: NFTCollectionPage

  },
  {
    path: '/Market',
    name: 'Market',
    component: MarketPage

  },
  {
    path: '/SearchUser',
    name: 'SearchUser',
    component: SearchUserPage
  },
  {
    path: '/Notifications',
    name: 'NotificationPage',
    component: NotificationPage
  },
  {
    path: '/myListings/:userId',
    name: 'MyListings',
    component: myListings
  },
  {
    path: '/myTradeOffers',
    name: 'MyTradeOffers',
    component: TradeOffersPage
  },
  {
    path: '/myReceipts',
    name: 'MyReceipt',
    component: ReceiptsPage
  },
  {
    path: '/myFavourites',
    name: 'FavouriteListings',
    component: FavouriteListings
  },
  {
    path: '/adminPageListing',
    name: 'AdminPageListing',
    component: AdminPageListing,
  },
  {
    path: '/adminPageUserAccount',
    name: 'adminPageUserAccount',
    component: AdminPageUserAccount,
  },
  {
    path: '/profile',
    name: 'profile',
    component: profilePage,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
export default router
