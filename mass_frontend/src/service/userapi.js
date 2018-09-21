import axios from 'axios'

// axios默认配置
axios.defaults.baseURL = ''
axios.defaults.timeout = 10000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

export const getUserBySno = param => {
  return axios({
    method: 'get',
    url: '/api/user/detail',
    params: param
  })
}

export const getAll = param => {
  return axios({
    method: 'post',
    url: 'api/user',
    data: param
  })
}


export const update = param => {
  return axios({
    method: 'post',
    url: 'api/user/update',
    data: param
  })
}


export const resetpwd = param => {
  return axios({
    method: 'put',
    url: 'api/user/pwd',
    params: param
  })
}
export const getDetail = param => {
  return axios({
    method: 'get',
    url: 'api/user/detail',
    params: param
  })
}


export const setImg = param => {
  return axios({
    method: 'post',
    url: 'api/user/head-img',
    params: param
  })
}

export const importFile = param => {
  return axios({
    method: 'post',
    url: '/api/file/user/import',
    data: param
  })
}


export const exportFile = () => {
  axios({
    url: '/api/file/user/export',
    method: 'get',
    responseType: 'blob'
  }).then((res) => {
    var blob = new Blob([res.data], {type: res.headers['content-type']});
    var downloadElement = document.createElement('a');
    var href = window.URL.createObjectURL(blob); //创建下载的链接
    downloadElement.href = href;
    downloadElement.download = 'u1.xls'; //下载后文件名
    document.body.appendChild(downloadElement);
    downloadElement.click(); //点击下载
    document.body.removeChild(downloadElement); //下载完成移除元素
    window.URL.revokeObjectURL(href); //释放掉blob对象
  });
}
