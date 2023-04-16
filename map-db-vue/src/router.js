import { createRouter, createWebHistory } from 'vue-router'
import MapView from './components/MapView'
import AddInfo from './components/AddInfo'
import InfoList from './components/InfoList'
import InfoDetail from './components/InfoDetail'
import InfoEdit from './components/InfoEdit'


const routes = [
    {
        path: '/map-db',
        name: 'MapView',
        component: MapView,
        children: [
            {
                path: 'add/:latitude/:longitude',
                name: 'AddInfo',
                component: AddInfo
            },
            {
                path: 'detail/:buildingId',
                name: 'InfoDetail',
                component: InfoDetail
            },
            {
                path: 'list',
                name: 'InfoList',
                component: InfoList
            },
            {
                path: 'edit/:buildingId/:name/:address/:latitude/:longitude',
                name: 'InfoEdit',
                component: InfoEdit
            }
        ]
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router