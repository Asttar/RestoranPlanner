import { createRouter, createWebHistory } from 'vue-router'
import Booking from "../components/Booking.vue";
import About from '../components/About.vue';
import ViewBooked from '../components/ViewBooked.vue';


const routes = [
    {
        path: "/",
        name: "Booking",
        component: Booking,
    },
    {
        path: "/about",
        name: "About",
        component: About,
    },
    {
        path: "/view",
        name: "ViewBooked",
        component: ViewBooked,
    },
    {
        path: "/:pathMatch(.*)*",
        redirect: "/",
    },

]

const router = createRouter({
    history: createWebHistory('/'),
    routes
})

export default router