import axios from 'axios'

// axios默认配置
axios.defaults.baseURL = ''
axios.defaults.timeout = 10000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

export const create = param => {
  return axios({
    method: 'post',
    url: '/api/register',
    data: param
  })
}
export const getById = param => {
  return axios({
    method: 'get',
    url: '/api/register/' + param,
  })
}
export const mycreate = param => {
  return axios({
    method: 'get',
    url: '/api/register/sno/',
    params: param
  })
}


export const sign = param => {
  return axios({
    method: "post",
    url: "/api/register/register",
    data: param
  })
}


export const getByActivityId = param => {
  return axios({
    method: 'get',
    url: '/api/register/activity/' + param,
  })
}

export const getSign = param => {
  return axios({
    method: "get",
    url: "/api/register/code/" + param,
  })
}

export const signQuery = param => {
  return axios({
    method: "post",
    url: "/api/register/query",
    data: param
  })
}

export const getResult = param => {
  return axios({
    method: "get",
    url: "/api/register/result/" + param.id,
    params: param
  })
}

export const down = param => {
  axios({
    url: '/api/export/register',
    method: 'post',
    data: param, // important
    responseType: 'blob'
  }).then((res) => {
    var blob = new Blob([res.data], {type: res.headers['content-type']});
    var downloadElement = document.createElement('a');
    var href = window.URL.createObjectURL(blob); //创建下载的链接
    downloadElement.href = href;
    downloadElement.download = param.name + '.xls'; //下载后文件名
    document.body.appendChild(downloadElement);
    downloadElement.click(); //点击下载
    document.body.removeChild(downloadElement); //下载完成移除元素
    window.URL.revokeObjectURL(href); //释放掉blob对象
  });
}




