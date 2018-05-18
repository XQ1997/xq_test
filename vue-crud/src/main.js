// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
//导入elementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//导入axios
import axios from 'axios'

//使用
Vue.use(ElementUI);

//设置自己的属性  在以后使用this.$http就相当于使用axios库
Vue.prototype.$http=axios

//配置axios的默认属性  默认的url前缀
axios.defaults.baseURL="http://localhost:9090"
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
