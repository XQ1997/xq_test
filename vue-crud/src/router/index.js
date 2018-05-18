import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import add from '@/components/add'
import edit from '@/components/edit'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/new',
      name: 'add',
      component: add
    },
    {
      path: '/edit/:id',
      name: 'edit',
      component: edit
    }
  ]
})
