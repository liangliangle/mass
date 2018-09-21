import axios from 'axios'

// axios默认配置
axios.defaults.baseURL = ''
axios.defaults.timeout = 10000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

export const create = param => {
  return axios({
    method: 'post',
    url: '/api/notice',
    data: param
  })
}


export const mycreate = param =>{
  return axios({
    method: 'get',
    url: '/api/notice/mycreate',
    params: param
  })
}


export const getAll = param =>{
  return axios({
    method: 'post',
    url: '/api/notice/query',
    data: param
  })
}


export const getById= param =>{
  ///api/notice/1
  return axios({
    method: 'get',
    url: '/api/notice/'+param.id,
    params: param
  })
}


export const getMy= param =>{
  ///api/notice/1
  return axios({
    method: 'get',
    url: '/api/notice/sno',
    params: param
  })
}


export  const getResult= param=>{
  return axios({
    method: 'get',
    url: '/api/notice/result/query',
    params: param
  })
}


export  const countResult= param=>{
  return axios({
    method: 'get',
    url: '/api/notice/result',
    params: param
  })
}



