// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import {
  router
} from './router'
import Vuex from 'vuex'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import ECharts from 'vue-echarts/components/ECharts';
import mavonEditor from 'mavon-editor'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import Vuemoment from 'vue-moment'



// markdown-it对象：md.s_markdown, md => mavonEditor实例
//                 or
//                 mavonEditor.markdownIt
import 'mavon-editor/dist/css/index.css'
// use
Vue.use(mavonEditor)
Vue.use(Vuemoment)
Vue.use(Vuex)
Vue.use(ElementUI)
Vue.use(ECharts)
//Vue.use(VueAxios, axios)
Vue.use(VueRouter)
Vue.use(VueResource)
//Vue.prototype.$http = axios
Vue.config.productionTip = false


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {
    App
  }
})

Vue.filter('getYMD', function (input) {
  return input.split(' ')[0];
})
