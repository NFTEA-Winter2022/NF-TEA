import Vue from 'vue'
import VueRouter from 'vue-router'
import HelloWorld from '../components/HelloWorld.vue'
import CreateAccountFront from '../components/CreateAccountFront';
import editPasswordAccount from "@/components/EditPasswordAccount";
import editUsernameAccount from "@/components/EditUsernameAccount";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'HelloWorld',
    component: HelloWorld
  },
  {
    path:'/Create',
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
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
