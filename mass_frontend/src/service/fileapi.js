import axios from 'axios'

// axios默认配置
axios.defaults.baseURL = ''
axios.defaults.timeout = 10000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'


export const uploadFile = param => {
  return axios({
    method: 'post',
    url: '/api/export/img',
    data: param
  })
}

