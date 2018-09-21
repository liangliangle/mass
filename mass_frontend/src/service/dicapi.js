import axios from 'axios'

// axios默认配置
axios.defaults.baseURL = ''
axios.defaults.timeout = 10000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'


export const getClasses = () => {
  return axios({
    method: 'get',
    url: '/api/dictionary/classes',
  })
}

export const addClasses = param => {
  return axios({
    method: 'post',
    url: '/api/dictionary/classes',
    data: param
  });
}

export  const editClasses= param =>{
  return axios({
    method: 'put',
    url: '/api/dictionary/classes',
    data: param
  });
}

export  const deleteClasses =param=>{
  return axios({
    method: 'delete',
    url: '/api/dictionary/classes',
    params: param
  });
}
