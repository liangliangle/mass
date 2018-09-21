import axios from 'axios'

// axios默认配置
axios.defaults.baseURL = ''
axios.defaults.timeout = 10000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

export const create = param => {
  return axios({
    method: 'post',
    url: '/api/vote',
    data: param
  })
}

export const getByQuery = param => {
  return axios({
    method: 'post',
    url: 'api/vote/query',
    data: param
  })
}

export const getBySno = param => {
  return axios({
    method: 'get',
    url: 'api/vote/sno',
    params: param
  })
}

export const voteDo = param => {
  return axios({
    method: 'post',
    url: 'api/vote/vote',
    data: param
  })
}
export const getById = param => {
  return axios({
    method: 'get',
    url: 'api/vote/'+param,
  })
}
export const getByMy = param => {
  return axios({
    method: 'get',
    url: 'api/vote/my',
    params: param
  })
}

export const groupById = param => {
  return axios({
    method: 'get',
    url: '/api/vote/group/'+param,
  })
}

export const getResult = param => {
  return axios({
    method: 'get',
    url: 'api/vote/result/'+param.id,
    params: param
  })
}





