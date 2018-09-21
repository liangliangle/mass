import axios from 'axios'

// axios默认配置
axios.defaults.baseURL = ''
axios.defaults.timeout = 10000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

export const getAll = param => {
  return axios({
    method: 'get',
    url: '/api/mass',
    params: param
  })
}

export const getAllNoPage = () => {
  return axios({
    method: 'get',
    url: '/api/mass/all',
  })
}

export const create = param => {
  return axios({
    method: 'post',
    url: '/api/mass',
    data: param
  })
}


export const mycreate = param => {
  return axios({
    method: 'get',
    url: '/api/mass/mycreate',
    params: param
  })
}

export const getAudit = param => {
  return axios({
    method: 'get',
    url: '/api/mass/check',
    params: param
  })
}


export const checkMass = param => {
  return axios({
    method: 'post',
    url: '/api/mass/check',
    params: param
  })
}

export const getCheckUser = param => {
  return axios({
    method: 'get',
    url: '/api/mass/org/check',
    params: param
  })
}

export const checkUser = param => {
  return axios({
    method: 'post',
    url: '/api/mass/org/check/' + param.id,
    params: param
  })
}


export const setPresident = param => {
  return axios({
    method: 'put',
    url: '/api/mass/masspresent',
    params: param
  })
}

export const addMass = param => {
  return axios({
    method: 'post',
    url: '/api/mass/org',
    data: param
  })
}


export const getUser = param => {
  return axios({
    method: 'get',
    url: '/api/mass/user',
    params: param
  })
}


export const getMyMass = param => {
  return axios({
    method: 'post',
    url: '/api/mass/org/myapplys',
    params: param
  })
}

export  const exitMass = param =>{
  return axios({
    method: 'delete',
    url: '/api/mass/org/user',
    params: param
  })
}

