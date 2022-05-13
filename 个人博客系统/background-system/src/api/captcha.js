// 向服务器发送请求，获取验证码
//request封装了一个方法axios
import request from '@/utils/request'

export function getCaptcha(){
    return request({
        url: '/res/captcha',
        method: 'get'
      })
}