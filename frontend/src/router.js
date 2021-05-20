
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import PaymentManager from "./components/PaymentManager"

import RoomManager from "./components/roomManager"


import GuestManagementPage from "./components/GuestManagementPage"
import BookManager from "./components/BookManager"

import NotificationManager from "./components/NotificationManager"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/Payment',
                name: 'PaymentManager',
                component: PaymentManager
            },

            {
                path: '/room',
                name: 'roomManager',
                component: roomManager
            },


            {
                path: '/GuestManagementPage',
                name: 'GuestManagementPage',
                component: GuestManagementPage
            },
            {
                path: '/Book',
                name: 'BookManager',
                component: BookManager
            },

            {
                path: '/Notification',
                name: 'NotificationManager',
                component: NotificationManager
            },



    ]
})
