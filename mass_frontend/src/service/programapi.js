import axios from 'axios'

// axios默认配置
axios.defaults.baseURL = ''
axios.defaults.timeout = 10000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

export const getByActivityId = param => {
  return axios({
    method: 'get',
    url: '/api/program/',
    data: param
  })
}
export const editprogram = param => {
  return axios({
    method: 'put',
    url: '/api/program/',
    data: param
  })
}
export const adds = param => {
  return axios({
    method: 'post',
    url: '/api/program/list',
    data: param
  })
}

export const getById = param => {
  return axios({
    method: 'get',
    url: '/api/program/' + param
  })
}
export const create = param => {
  return axios({
    method: 'post',
    url: '/api/program',
    data: param
  })
}

export const getByActivity = param => {
  return axios({
    method: 'get',
    url: '/api/program',
    params: param
  })
}

export const getList = param => {
  return axios({
    method: 'get',
    url: '/api/program',
    params: param
  })
}

export const delProgram = param => {
  return axios({
    method: 'delete',
    url: '/api/program/' + param,
  })
}
export const delMem = param => {
  return axios({
    method: 'delete',
    url: '/api/program/mem/' + param,
  })
}

export const addMem = param => {
  return axios({
    method: 'post',
    url: '/api/program/mem/' + param.id,
    data: param.data
  })
}
