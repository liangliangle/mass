import axios from 'axios'

// axios默认配置
axios.defaults.baseURL = ''
axios.defaults.timeout = 10000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

export const getMenus = () => {
  return axios({
    method: 'get',
    url: '/api/menus'
  })
}

export const getRole = param => {
  return axios({
    method: 'get',
    url: '/api/role',
    params: param
  })
}

export const getRoleById = param => {
  return axios({
    method: 'get',
    url: 'api/menus/role',
    params: param
  })
}

export const addRoleMenus = param => {
  return axios({
    method: 'post',
    async: false,
    url: 'api/menus/role',
    data: param
  })
}


export  const setUserRole = param =>{
  return axios({
    method: 'post',
    async: false,
    url: 'api/role/username',
    data: param
  })
}


export const getFilteRole = () => {
  return axios({
    method: 'get',
    url: '/api/role/filte',
  })
}
