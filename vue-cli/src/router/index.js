import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import home from '@/components/home'

Vue.use(Router)
//关于系统路由配置在当前文件进行
//export是ES6的新特性，指的是在导入该组件时会返回一个东西，相当于java中导一个包里的类
export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      //这里的component为import的名字
      component: home
    }
  ]
})
