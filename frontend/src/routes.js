import Login from './views/Login.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Main from './views/Main.vue'
import Org from './views/nav1/Org.vue'
import Rcm from './views/nav1/Rcm.vue'
import Brd from './views/nav1/Brd.vue'

let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    {
        path: '/',
        component: Home,
        name: '导航',
        iconCls: 'el-icon-message',//图标样式class
        children: [
            { path: '/main', component: Main, name: '主页', hidden: true },
            { path: '/rcm', component: Rcm, name: '赛事信息维护' },
            { path: '/org', component: Org, name: '媒体机构维护' },
            { path: '/brd', component: Brd, name: '公告维护' },
        ]
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export default routes;