import request from '@/utils/request'

export function loginApi(data) {
  return request({
    url: '/api/admin/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/api/admin/whoami',
    method: 'get',
    //每一次请求都需要把token带到请求头里面 在request里面配置一下
   /*  params: { token } */
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}
