import Vue from 'vue';
import Vuex from 'vuex';

import AppStore from './modules/AppStore';

Vue.use(Vuex);

const store=new Vuex.Store({
	strict: false, //开启严格模式，state的变更只能通过mutation提交，不然报异常
    modules:{
        'app':AppStore
    }
});

export default store;
