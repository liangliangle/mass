import axios from 'axios'

// axios默认配置
axios.defaults.baseURL = ''
axios.defaults.timeout = 10000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

export const getNotyet = param => {
  return axios({
    method: 'get',
    url: '/api/finance/notyet',
    params: param
  })
}

export const create = param => {
  return axios({
    method: 'post',
    url: '/api/finance',
    data: param
  })
}
export const getById = param => {
  return axios({
    method: 'get',
    url: '/api/finance/' + param
  })
}
export const audit = param => {
  return axios({
    method: 'put',
    url: '/api/finance/audit',
    data: param
  })
}
export const mymass = param => {
  return axios({
    method: 'post',
    url: '/api/finance/mymass',
    data: param
  })
}

export const edit = param => {
  return axios({
    method: 'put',
    url: '/api/finance',
    data: param
  })
}

export const settle = param => {
  return axios({
    method: 'put',
    url: '/api/finance/settle/' + param

  })
}

export const query = param => {
  return axios({
    method: 'post',
    url: '/api/finance/query',
    data: param
  })
}
