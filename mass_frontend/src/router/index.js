import Vue from 'vue'
import Router from 'vue-router'
import {routers} from './router'

Vue.use(Router);
const RouterConfig = {
  routes: routers
}
export const router = new Router(RouterConfig);

//路由过滤，验证登录和访问权限
router.beforeEach((to, from, next) => {
  if (to.path == '/404') {
    next();
    return;
  }
  let user = JSON.parse(sessionStorage.getItem('user'));
  if (!user && to.meta.isLogin) {
    next({
      path: '/login'
    });
  } else if (to.meta.isLogin && to.meta.isAuth) {
    //权限验证，深入最多三级菜单判断
    let isAuth = false;
    let find = false;
    user.menus.forEach(function (menu) {
      if (menu.url == to.path) {
        isAuth = true;
        find = true;
      }
      if (menu.children) {
        menu.children.forEach(function (menu2) {
          if (menu2.url == to.path) {
            isAuth = true;
            find = true;
          }
          if (menu2.children) {
            menu2.children.forEach(function (menu3) {
              if (menu3.url == to.path) {
                isAuth = true;
                find = true;
              }
            });
          }
        });
      }
    });
    if (isAuth ) {
      next();
    }else if(!find){
      next();
    } else {
      next('/message');
    }
  } else {
    next();
  }
})
