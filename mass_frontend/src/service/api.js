import axios from 'axios';

import {Notification} from 'element-ui';


//axios默认配置
axios.defaults.baseURL = ''
axios.defaults.timeout = 10000

// http request 拦截器
axios.interceptors.request.use(
  config => {
    if (sessionStorage.getItem("user") != null) {
      //从sessionStorage中加载用户menu信息
      let user = JSON.parse(sessionStorage.getItem("user"));
      config.headers.Authorization = user.tocken;
    }
    return config;
  },
  err => {
    return Promise.reject(err);
  }
);

// http response 拦截器
axios.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    if (error.response) {
      if(error.response.data.message) {
        Notification({
          title: '提示',
          message: error.response.data.message,
          type: 'error'
        })
      }else{
        Notification({
          title: '提示',
          message: error.response.data,
          type: 'error'
        })
      }
      switch (error.response.status) {
        case 401:
          sessionStorage.removeItem('user');
          let curWwwPath = window.document.location.href;
          //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
          let pathName = window.document.location.pathname;
          let pos = curWwwPath.indexOf(pathName);
          //获取主机地址，如： http://localhost:8083
          let localhostPaht = curWwwPath.substring(0, pos);
          location.replace(localhostPaht);
      }


    }
    return Promise.reject(error.response.data)
  }
);


export const requestLogin = params => {
  return axios({
    method: 'get',
    headers: {
      'auth': params
    },
    url: '/api/user/login'
  })
}

export const checkRole = param => {
  if(param.tocken){
    return axios({
      method: 'get',
      url: '/api/user/tocken-login',
      headers: {
        'Authorization': param.tocken
      },
      params: param
    })
  }else{
    return axios({
      method: 'get',
      url: '/api/user/tocken-login',
      params: param
    })
  }

}
