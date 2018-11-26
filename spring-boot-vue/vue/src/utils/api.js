/**
 *@author :hly
 *@gkhub :https://gkhub.com/huangliangyun
 @blog :blog.csdn.net/Sirius_hly
 *@date :2018/11/24
 */

import axios from 'axios'

let base = ''
export const postRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function (data) {
      // Do whatever you want to transform the data
      let newData= ''
      for (let k in data) {
        newData+= encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&'
      }
      return newData
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

export const getRequest = (url,params) => {
  return axios({
    method: 'get',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function (data) {
      let newData= ''
      for (let k in data) {
        newData+= encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&'
      }
      return newData
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  });
}

export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function (data) {
      let newData= ''
      for (let k in data) {
        newData+= encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&'
      }
      return newData
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  });
}

export const deleteRequest = (url) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`
  });
}

export const uploadFileRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
}
