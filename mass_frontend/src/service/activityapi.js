import axios from 'axios'

// axios默认配置
axios.defaults.baseURL = ''
axios.defaults.timeout = 10000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

export const getNotyet = param => {
  return axios({
    method: 'post',
    url: '/api/activits/notyet',
    params: param
  })
}

export const createAvtivity = param => {
  return axios({
    method: 'post',
    url: '/api/activits',
    data: param
  })
}
export const getById = param => {
  return axios({
    method: 'get',
    url: '/api/activits/' + param
  })
}
export const audit = param => {
  return axios({
    method: 'post',
    url: '/api/activits/audit',
    data: param
  })
}
export const mymass = param => {
  return axios({
    method: 'post',
    url: '/api/activits/mymass',
    params: param
  })
}

export const editAvtivity = param => {
  return axios({
    method: 'put',
    url: '/api/activits',
    data: param
  })
}

export const queryActivity = param => {
  return axios({
    method: 'post',
    url: '/api/activits/query',
    data: param
  })
}

export const latelyAvtivity = param => {
  return axios({
    method: 'get',
    url: '/api/activits/lately'
  })
}
